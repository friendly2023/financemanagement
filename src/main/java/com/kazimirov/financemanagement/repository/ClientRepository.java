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

    @Query("SELECT c FROM ClientEntity c WHERE LOWER(c.linkToProfile) = LOWER(:linkToProfile)")
    Optional<ClientEntity> findByLinkToProfileIgnoreCase(@Param("linkToProfile") String linkToProfile);

    @Query("SELECT c FROM ClientEntity c WHERE LOWER(c.name) = LOWER(:name) AND LOWER(c.note) = LOWER(:note)")
    Optional<ClientEntity> findByNameAndNoteIgnoreCase(@Param("name") String name, @Param("note") String note);

    @Query("SELECT c FROM ClientEntity c WHERE LOWER(c.name) = LOWER(:name)")
    Optional<ClientEntity> findByNameIgnoreCase(@Param("name") String name);

    @Query("SELECT c FROM ClientEntity c " +
            "WHERE LOWER(c.name) LIKE %:query% " +
            "OR LOWER(c.linkToProfile) LIKE %:query% " +
            "OR LOWER(c.note) LIKE %:query%")
    List<ClientEntity> searchByNameOrLinkOrNote(@Param("query") String query);
}
