package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.dto.OrderResponse;
import com.kazimirov.financemanagement.entity.OrderEntity;
import com.kazimirov.financemanagement.entity.OrderStatus;
import com.kazimirov.financemanagement.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private ValidatorForOverdueOrders validatorForOverdueOrders;
    private ValidatorForVerifyNote validatorForVerifyNote;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        ValidatorForOverdueOrders validatorForOverdueOrders,
                        ValidatorForVerifyNote validatorForVerifyNote) {
        this.orderRepository = orderRepository;
        this.validatorForOverdueOrders = validatorForOverdueOrders;
        this.validatorForVerifyNote = validatorForVerifyNote;
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
                .orElseThrow(() -> new IllegalArgumentException("Заказ не найден с id: " + id));
    }


    public List<OrderResponse> getAllOrdersSortedByDueDate() {
        List<OrderEntity> orderEntities = orderRepository.findAllByOrderByDueDate();

        return orderEntities.stream()
                .map(OrderResponseFactory::mapToOrderResponse)
                .collect(Collectors.toList());
    }

    public List<OrderResponse> getForClientOrdersByIdSortedByDueDate(Long clientId) {
        List<OrderEntity> orderEntities = orderRepository.findByClientEntity_Id(clientId);

        return orderEntities.stream()
                .map(OrderResponseFactory::mapToOrderResponse)
                .collect(Collectors.toList());
    }

    public List<OrderEntity> getOrdersByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    public Optional<OrderEntity> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    //TODO убрать валидацию при удалении в отдельный класс
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new IllegalArgumentException("Задача с ID " + id + " не найдена.");
        }
        orderRepository.deleteById(id);
    }
}
