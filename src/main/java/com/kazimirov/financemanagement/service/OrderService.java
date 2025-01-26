package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.dto.OrderDetailsResponse;
import com.kazimirov.financemanagement.dto.OrderResponse;
import com.kazimirov.financemanagement.entity.OrderEntity;
import com.kazimirov.financemanagement.entity.ProductEntity;
import com.kazimirov.financemanagement.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private ValidatorForOverdueOrders validatorForOverdueOrders;
    private ValidatorForVerifyNote validatorForVerifyNote;
    private OrderResponseFactory orderResponseFactory;
    private OrderDetailsResponseFactory orderDetailsResponseFactory;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        ValidatorForOverdueOrders validatorForOverdueOrders,
                        ValidatorForVerifyNote validatorForVerifyNote,
                        OrderResponseFactory orderResponseFactory,
                        OrderDetailsResponseFactory orderDetailsResponseFactory) {
        this.orderRepository = orderRepository;
        this.validatorForOverdueOrders = validatorForOverdueOrders;
        this.validatorForVerifyNote = validatorForVerifyNote;
        this.orderResponseFactory = orderResponseFactory;
        this.orderDetailsResponseFactory = orderDetailsResponseFactory;
    }

    public OrderEntity createOrder(OrderEntity orderEntity) {
        validatorForOverdueOrders.validate(orderEntity);
        validatorForVerifyNote.validate(orderEntity);
        return orderRepository.save(orderEntity);
    }

    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderEntity searchOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + id));
    }


    public List<OrderResponse> getAllOrdersSortedByDueDate() {
        List<OrderEntity> orderEntities = orderRepository.findAllByOrderByDueDateDesc();

        return orderEntities.stream()
                .map(orderResponseFactory::mapToOrderResponse)
                .collect(Collectors.toList());
    }

    public List<OrderResponse> getForClientOrdersByIdSortedByDueDate(Long clientId) {
        List<OrderEntity> orderEntities = orderRepository.findByClientEntity_Id(clientId);

        return orderEntities.stream()
                .map(orderResponseFactory::mapToOrderResponse)
                .collect(Collectors.toList());
    }

    public OrderDetailsResponse getOrderDetailsById(Long id) {
        return orderDetailsResponseFactory.mapToOrderDetailsResponse(searchOrderById(id));
    }

    public List<ProductEntity> findAllProductsByOrderId(Long orderId) {
        return orderRepository.findAllProductsByOrderId(orderId);
    }

    //TODO убрать валидацию при удалении в отдельный класс
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new IllegalArgumentException("Задача с ID " + id + " не найдена.");
        }
        orderRepository.deleteById(id);
    }
}
