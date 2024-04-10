package com.moretto.bruno.myspringapi.service.impl;

import com.moretto.bruno.myspringapi.entity.ProductGroup;
import com.moretto.bruno.myspringapi.exception.EntityAlreadyExistsException;
import com.moretto.bruno.myspringapi.exception.EntityNotFoundException;
import com.moretto.bruno.myspringapi.exception.EntityWithoutIdException;
import com.moretto.bruno.myspringapi.repository.ProductGroupRepository;
import com.moretto.bruno.myspringapi.service.ProductGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ProductGroupServiceImpl implements ProductGroupService {

    private static final String PRESENTABLE_NAME = "Product group";

    private final ProductGroupRepository productGroupRepository;

    @Override
    public ProductGroup create(ProductGroup productGroup) {
        if (productGroup.getId() != null || productGroupRepository.existsByName(productGroup.getName())) {
            throw new EntityAlreadyExistsException(PRESENTABLE_NAME);
        }

        return productGroupRepository.save(productGroup);
    }

    @Override
    public ProductGroup update(ProductGroup productGroup) {
        if (productGroup.getId() == null) {
            throw new EntityWithoutIdException(PRESENTABLE_NAME);
        }

        if (!productGroupRepository.existsById(productGroup.getId())) {
            throw new EntityNotFoundException(PRESENTABLE_NAME, productGroup.getId());
        }

        return productGroupRepository.save(productGroup);
    }

    @Override
    public ProductGroup getById(long id) {
        return productGroupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(PRESENTABLE_NAME, id));
    }

    @Override
    public Page<ProductGroup> findAll(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
        return productGroupRepository.findAll(pageable);
    }

}
