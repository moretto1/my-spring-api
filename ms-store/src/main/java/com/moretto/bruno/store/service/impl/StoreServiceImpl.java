package com.moretto.bruno.store.service.impl;

import com.moretto.bruno.store.entity.Store;
import com.moretto.bruno.store.exception.EntityAlreadyExistsException;
import com.moretto.bruno.store.exception.EntityNotFoundException;
import com.moretto.bruno.store.exception.EntityWithoutIdException;
import com.moretto.bruno.store.repository.StoreRepository;
import com.moretto.bruno.store.service.StoreService;
import com.moretto.bruno.store.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private static final String PRESENTABLE_NAME = "Store";

    private final StoreRepository storeRepository;

    @Override
    public Store create(Store store) {
        if (store.getId() != null) {
            throw new EntityAlreadyExistsException(PRESENTABLE_NAME);
        }

        store.setSecretToken(StringUtils.generateRandomString(16));

        return storeRepository.save(store);
    }

    @Override
    public Store update(Store store) {
        if (store.getId() == null) {
            throw new EntityWithoutIdException(PRESENTABLE_NAME);
        }

        if (!storeRepository.existsById(store.getId())) {
            throw new EntityNotFoundException(PRESENTABLE_NAME, store.getId());
        }

        return storeRepository.save(store);
    }

    @Override
    public Store getById(long id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(PRESENTABLE_NAME, id));
    }

    @Override
    public Page<Store> findAll(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
        return storeRepository.findAll(pageable);
    }

}
