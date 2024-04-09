package com.moretto.bruno.myspringapi.controller;

import com.moretto.bruno.myspringapi.dto.StoreDTO;
import com.moretto.bruno.myspringapi.entity.Store;
import com.moretto.bruno.myspringapi.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    private final ModelMapper mapper;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid StoreDTO storeDTO) {
        Store store = mapper.map(storeDTO, Store.class);
        store = storeService.create(store);

        URI selfURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(store.getId())
                .toUri();

        return ResponseEntity.created(selfURI).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid StoreDTO storeDTO) {
        Store store = mapper.map(storeDTO, Store.class);
        store = storeService.update(store);

        URI selfURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(store.getId())
                .toUri();

        return ResponseEntity.ok().location(selfURI).build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<StoreDTO> getById(@PathVariable long id) {
        Store store = storeService.getById(id);

        StoreDTO storeDTO = mapper.map(store, StoreDTO.class);

        return ResponseEntity.ok(storeDTO);
    }

    @GetMapping
    public ResponseEntity<Page<StoreDTO>> findAll(@RequestParam int pageNumber, @RequestParam int pageSize) {
        Page<Store> stores = storeService.findAll(pageNumber, pageSize);
        Page<StoreDTO> storeDTOS = stores.map(store -> mapper.map(store, StoreDTO.class));

        return ResponseEntity.ok(storeDTOS);
    }

}
