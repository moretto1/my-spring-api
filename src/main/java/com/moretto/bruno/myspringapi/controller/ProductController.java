package com.moretto.bruno.myspringapi.controller;

import com.moretto.bruno.myspringapi.dto.ProductDTO;
import com.moretto.bruno.myspringapi.entity.Product;
import com.moretto.bruno.myspringapi.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "/products")
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductService productService;
    private final ModelMapper mapper;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid ProductDTO productDTO) {
        Product product = mapper.map(productDTO, Product.class);
        product = productService.create(product);

        URI selfURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();

        return ResponseEntity.created(selfURI).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid ProductDTO productDTO) {
        Product product = mapper.map(productDTO, Product.class);
        product = productService.update(product);

        URI selfURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();

        return ResponseEntity.ok().location(selfURI).build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable long id) {
        Product product = productService.getById(id);

        ProductDTO productDTO = mapper.map(product, ProductDTO.class);

        return ResponseEntity.ok(productDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(@RequestParam int pageNumber, @RequestParam int pageSize) {
        Page<Product> productGroups = productService.findAll(pageNumber, pageSize);
        Page<ProductDTO> productGroupDTOs = productGroups.map(product -> mapper.map(product, ProductDTO.class));

        return ResponseEntity.ok(productGroupDTOs);
    }
    
}
