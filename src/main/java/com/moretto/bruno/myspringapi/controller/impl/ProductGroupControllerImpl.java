package com.moretto.bruno.myspringapi.controller.impl;

import com.moretto.bruno.myspringapi.controller.ProductGroupController;
import com.moretto.bruno.myspringapi.dto.ProductGroupDTO;
import com.moretto.bruno.myspringapi.entity.ProductGroup;
import com.moretto.bruno.myspringapi.service.ProductGroupService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class ProductGroupControllerImpl implements ProductGroupController {

    private final ProductGroupService productGroupService;
    private final ModelMapper mapper;

    @Override
    public ResponseEntity<Void> create(ProductGroupDTO productGroupDTO) {
        ProductGroup productGroup = mapper.map(productGroupDTO, ProductGroup.class);
        productGroup = productGroupService.create(productGroup);

        URI selfURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productGroup.getId())
                .toUri();

        return ResponseEntity.created(selfURI).build();
    }

    @Override
    public ResponseEntity<Void> update(ProductGroupDTO productGroupDTO) {
        ProductGroup productGroup = mapper.map(productGroupDTO, ProductGroup.class);
        productGroup = productGroupService.update(productGroup);

        URI selfURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productGroup.getId())
                .toUri();

        return ResponseEntity.ok().location(selfURI).build();
    }

    @Override
    public ResponseEntity<ProductGroupDTO> getById(long id) {
        ProductGroup productGroup = productGroupService.getById(id);

        ProductGroupDTO productGroupDTO = mapper.map(productGroup, ProductGroupDTO.class);

        return ResponseEntity.ok(productGroupDTO);
    }

    @Override
    public ResponseEntity<Page<ProductGroupDTO>> findAll(int pageNumber, int pageSize) {
        Page<ProductGroup> productGroups = productGroupService.findAll(pageNumber, pageSize);
        Page<ProductGroupDTO> productGroupDTOs = productGroups.map(productGroup -> mapper.map(productGroup, ProductGroupDTO.class));

        return ResponseEntity.ok(productGroupDTOs);
    }

}
