package com.kazimirov.financemanagement.repository;

import com.kazimirov.financemanagement.model.ClientEntity;
import com.kazimirov.financemanagement.model.OrderEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ClientResponseEntityRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    private List<ClientEntity> createdClientEntities = new ArrayList<>();

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

        ClientEntity clientEntity = clientRepository.findById(1L).orElseThrow();
        OrderEntity orderEntity = clientEntity.getOrders().get(0);
        clientEntity.getOrders().remove(orderEntity);
        clientRepository.save(clientEntity);
    }
}
