package com.kazimirov.financemanagement.service;

import com.kazimirov.financemanagement.model.Client;
import com.kazimirov.financemanagement.model.Order;
import com.kazimirov.financemanagement.model.OrderStatus;
import com.kazimirov.financemanagement.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Order createOrder(Order order) {
        validationForOverdueOrders.validate(order);
        validationForVerifyDescription.validate(order);
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getAllOrdersSortedByDueDateDesc() {
        return orderRepository.findAllByOrderByDueDate();
    }


    public List<Order> getOrdersByTitle(String title) {
        return orderRepository.findByTitleContaining(title);
    }

    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    public Optional<Order> getOrderById(Long id) {
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
