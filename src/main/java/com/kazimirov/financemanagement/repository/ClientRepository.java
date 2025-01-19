package com.kazimirov.financemanagement.repository;

import com.kazimirov.financemanagement.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    List<ClientEntity> findAll();
    List<ClientEntity> findAllByOrderByIdDesc();

    Optional<ClientEntity> findByNameAndLinkToProfile(String name, String linkToProfile);

}
