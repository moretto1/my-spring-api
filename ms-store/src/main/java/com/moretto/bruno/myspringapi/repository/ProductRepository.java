package com.moretto.bruno.myspringapi.repository;

import com.moretto.bruno.myspringapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
