package com.moretto.bruno.store.service.impl;

import com.moretto.bruno.store.entity.Product;
import com.moretto.bruno.store.exception.EntityAlreadyExistsException;
import com.moretto.bruno.store.exception.EntityNotFoundException;
import com.moretto.bruno.store.exception.EntityWithoutIdException;
import com.moretto.bruno.store.repository.ProductRepository;
import com.moretto.bruno.store.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final String PRESENTABLE_NAME = "Product";

    private final ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        if (product.getId() != null) {
            throw new EntityAlreadyExistsException(PRESENTABLE_NAME);
        }

        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        if (product.getId() == null) {
            throw new EntityWithoutIdException(PRESENTABLE_NAME);
        }

        if (!productRepository.existsById(product.getId())) {
            throw new EntityNotFoundException(PRESENTABLE_NAME, product.getId());
        }

        return productRepository.save(product);
    }

    @Override
    public Product getById(long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(PRESENTABLE_NAME, id));
    }

    @Override
    public Page<Product> findAll(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
        return productRepository.findAll(pageable);
    }

}
