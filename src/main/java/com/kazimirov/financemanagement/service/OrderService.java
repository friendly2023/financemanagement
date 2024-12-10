package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.dto.OrderResponse;
import com.kazimirov.financemanagement.model.OrderEntity;
import com.kazimirov.financemanagement.model.OrderStatus;
import com.kazimirov.financemanagement.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private ValidationForOverdueOrders validationForOverdueOrders;
    private ValidationForVerifyDescription validationForVerifyDescription;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        ValidationForOverdueOrders validationForOverdueOrders,
                        ValidationForVerifyDescription validationForVerifyDescription) {
        this.orderRepository = orderRepository;
        this.validationForOverdueOrders = validationForOverdueOrders;
        this.validationForVerifyDescription = validationForVerifyDescription;
    }

    public OrderEntity createOrder(OrderEntity orderEntity) {
        validationForOverdueOrders.validate(orderEntity);
        validationForVerifyDescription.validate(orderEntity);
        return orderRepository.save(orderEntity);
    }

    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<OrderResponse> getAllOrdersSortedByDueDate() {
        List<OrderEntity> orderEntities = orderRepository.findAllByOrderByDueDate();

        return orderEntities.stream()
                .map(OrderDTOFactory::mapToOrderDTO)
                .collect(Collectors.toList());
    }

    public List<OrderResponse> getForClientOrdersByIdSortedByDueDate(Long clientId) {
        List<OrderEntity> orderEntities = orderRepository.findByClientEntity_Id(clientId);

        return orderEntities.stream()
                .map(OrderDTOFactory::mapToOrderDTO)
                .collect(Collectors.toList());
    }


    public List<OrderEntity> getOrdersByTitle(String title) {
        return orderRepository.findByTitleContaining(title);
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
