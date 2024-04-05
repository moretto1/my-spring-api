package com.moretto.bruno.myspringapi.controller;

import com.moretto.bruno.myspringapi.dto.ProductGroupDTO;
import com.moretto.bruno.myspringapi.entity.ProductGroup;
import com.moretto.bruno.myspringapi.mapper.DefaultModelMapper;
import com.moretto.bruno.myspringapi.service.ProductGroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(path = "/product-groups")
@RequiredArgsConstructor
public class ProductGroupController {

    private final ProductGroupService productGroupService;
    private final ModelMapper mapper;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid ProductGroupDTO productGroupDTO) {
        ProductGroup productGroup = mapper.map(productGroupDTO, ProductGroup.class);
        productGroup = productGroupService.create(productGroup);

        URI selfURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productGroup.getId())
                .toUri();

        return ResponseEntity.created(selfURI).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid ProductGroupDTO productGroupDTO) {
        ProductGroup productGroup = mapper.map(productGroupDTO, ProductGroup.class);
        productGroup = productGroupService.update(productGroup);

        URI selfURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productGroup.getId())
                .toUri();

        return ResponseEntity.ok().location(selfURI).build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductGroupDTO> getById(@PathVariable long id) {
        ProductGroup productGroup = productGroupService.getById(id);

        ProductGroupDTO productGroupDTO = mapper.map(productGroup, ProductGroupDTO.class);

        return ResponseEntity.ok(productGroupDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ProductGroupDTO>> findAll(@RequestParam int pageNumber, @RequestParam int pageSize) {
        Page<ProductGroup> productGroups = productGroupService.findAll(pageNumber, pageSize);
        Page<ProductGroupDTO> productGroupDTOs = productGroups.map(productGroup -> mapper.map(productGroup, ProductGroupDTO.class));

        return ResponseEntity.ok(productGroupDTOs);
    }

}
