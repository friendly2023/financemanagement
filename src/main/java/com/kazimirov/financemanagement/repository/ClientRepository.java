package com.kazimirov.financemanagement.repository;

import com.kazimirov.financemanagement.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    List<ClientEntity> findAll();
    List<ClientEntity> findAllByOrderByIdDesc();

    Optional<ClientEntity> findByNameAndLinkToProfile(String name, String linkToProfile);

    @Query("SELECT c FROM ClientEntity c " +
            "WHERE LOWER(c.name) LIKE %:query% " +
            "OR LOWER(c.linkToProfile) LIKE %:query% " +
            "OR LOWER(c.note) LIKE %:query%")
    List<ClientEntity> searchByNameOrLinkOrNote(@Param("query") String query);
}
