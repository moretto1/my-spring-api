package com.moretto.bruno.store.service;

import com.moretto.bruno.store.entity.ProductGroup;
import org.springframework.data.domain.Page;

public interface ProductGroupService {

    ProductGroup create(ProductGroup productGroup);

    ProductGroup update(ProductGroup productGroup);

    ProductGroup getById(long id);

    Page<ProductGroup> findAll(int pageNumber, int pageSize);

}
