package com.moretto.bruno.myspringapi.controller.impl;

import com.moretto.bruno.myspringapi.controller.ProductController;
import com.moretto.bruno.myspringapi.dto.ProductDTO;
import com.moretto.bruno.myspringapi.entity.Product;
import com.moretto.bruno.myspringapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController {
    
    private final ProductService productService;
    private final ModelMapper mapper;

    @Override
    public ResponseEntity<Void> create(ProductDTO productDTO) {
        Product product = mapper.map(productDTO, Product.class);
        product = productService.create(product);

        URI selfURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();

        return ResponseEntity.created(selfURI).build();
    }

    @Override
    public ResponseEntity<Void> update(ProductDTO productDTO) {
        Product product = mapper.map(productDTO, Product.class);
        product = productService.update(product);

        URI selfURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();

        return ResponseEntity.ok().location(selfURI).build();
    }

    @Override
    public ResponseEntity<ProductDTO> getById(long id) {
        Product product = productService.getById(id);

        ProductDTO productDTO = mapper.map(product, ProductDTO.class);

        return ResponseEntity.ok(productDTO);
    }

    @Override
    public ResponseEntity<Page<ProductDTO>> findAll(int pageNumber, int pageSize) {
        Page<Product> productGroups = productService.findAll(pageNumber, pageSize);
        Page<ProductDTO> productGroupDTOs = productGroups.map(product -> mapper.map(product, ProductDTO.class));

        return ResponseEntity.ok(productGroupDTOs);
    }
    
}
