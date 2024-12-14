package com.kazimirov.financemanagement.repository;

import com.kazimirov.financemanagement.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByOrderEntityIsNull();

    Optional<ProductEntity> findByProductName(String productName);
}
