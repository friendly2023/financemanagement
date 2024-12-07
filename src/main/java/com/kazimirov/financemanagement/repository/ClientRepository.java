package com.kazimirov.financemanagement.repository;

import com.kazimirov.financemanagement.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findAll();
    List<Client> findAllByOrderByIdDesc();

}
