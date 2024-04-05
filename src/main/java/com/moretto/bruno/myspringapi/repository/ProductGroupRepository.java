package com.moretto.bruno.myspringapi.repository;

import com.moretto.bruno.myspringapi.entity.ProductGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductGroupRepository extends JpaRepository<ProductGroup, Long> {

    boolean existsByName(String name);

    boolean existsByIdAndName(Long id, String name);

}
