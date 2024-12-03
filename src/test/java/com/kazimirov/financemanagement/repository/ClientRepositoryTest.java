package com.kazimirov.financemanagement.repository;

import com.kazimirov.financemanagement.model.Client;
import com.kazimirov.financemanagement.model.Order;
import com.kazimirov.financemanagement.model.OrderStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    private List<Client> createdClients = new ArrayList<>();

    @Test
    public void testSaveClient() {
//        Client client = new Client();
//        client.setName("John Doe");
//        client.setLinkToProfile("/profile/john");
//        client.setNote("VIP клиент");
//
//        Order order1 = new Order();
//        order1.setTitle("Order A");
//        order1.setDueDate(LocalDate.now().plusDays(10));
//        order1.setStatus(OrderStatus.ONGOING);
//
//        Order order2 = new Order();
//        order2.setTitle("Order B");
//        order2.setDueDate(LocalDate.now().plusDays(5));
//        order2.setStatus(OrderStatus.OVERDUE);
//
//// Устанавливаем связь
//        client.addOrder(order1);
//        client.addOrder(order2);
//
//// Сохраняем клиента, каскадно сохранятся и заказы
//        clientRepository.save(client);

        Client client = clientRepository.findById(1L).orElseThrow();
        Order order = client.getOrders().get(0);
        client.getOrders().remove(order);
        clientRepository.save(client);
    }
}
