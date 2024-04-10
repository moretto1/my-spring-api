package com.moretto.bruno.store.service;

import com.moretto.bruno.store.entity.Store;
import org.springframework.data.domain.Page;

public interface StoreService {

    Store create(Store store);

    Store update(Store store);

    Store getById(long id);

    Page<Store> findAll(int pageNumber, int pageSize);

}
