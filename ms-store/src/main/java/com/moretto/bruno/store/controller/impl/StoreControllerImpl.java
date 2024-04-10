package com.moretto.bruno.store.controller.impl;

import com.moretto.bruno.store.controller.StoreController;
import com.moretto.bruno.store.dto.StoreDTO;
import com.moretto.bruno.store.entity.Store;
import com.moretto.bruno.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class StoreControllerImpl implements StoreController {

    private final StoreService storeService;
    private final ModelMapper mapper;

    @Override
    public ResponseEntity<Void> create(StoreDTO storeDTO) {
        Store store = mapper.map(storeDTO, Store.class);
        store = storeService.create(store);

        URI selfURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(store.getId())
                .toUri();

        return ResponseEntity.created(selfURI).build();
    }

    @Override
    public ResponseEntity<Void> update(StoreDTO storeDTO) {
        Store store = mapper.map(storeDTO, Store.class);
        store = storeService.update(store);

        URI selfURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(store.getId())
                .toUri();

        return ResponseEntity.ok().location(selfURI).build();
    }

    @Override
    public ResponseEntity<StoreDTO> getById(long id) {
        Store store = storeService.getById(id);

        StoreDTO storeDTO = mapper.map(store, StoreDTO.class);

        return ResponseEntity.ok(storeDTO);
    }

    @Override
    public ResponseEntity<Page<StoreDTO>> findAll(int pageNumber, int pageSize) {
        Page<Store> stores = storeService.findAll(pageNumber, pageSize);
        Page<StoreDTO> storeDTOS = stores.map(store -> mapper.map(store, StoreDTO.class));

        return ResponseEntity.ok(storeDTOS);
    }

}
