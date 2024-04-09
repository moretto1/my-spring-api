package com.moretto.bruno.myspringapi.service;

import com.moretto.bruno.myspringapi.entity.Product;
import org.springframework.data.domain.Page;

public interface ProductService {

    Product create(Product product);

    Product update(Product store);

    Product getById(long id);

    Page<Product> findAll(int pageNumber, int pageSize);

}
