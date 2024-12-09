package com.kazimirov.financemanagement.repository;

import com.kazimirov.financemanagement.model.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    List<ClientEntity> findAll();
    List<ClientEntity> findAllByOrderByIdDesc();

}
