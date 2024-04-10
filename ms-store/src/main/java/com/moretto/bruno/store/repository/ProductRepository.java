package com.moretto.bruno.store.repository;

import com.moretto.bruno.store.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
