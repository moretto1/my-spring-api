package com.moretto.bruno.myspringapi.controller;

import com.moretto.bruno.myspringapi.dto.StoreDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/stores")
@Tag(name = "Stores", description = "Represents stores endpoints.")
public interface StoreController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Creates a new store.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully completes the request.",
                            headers = @Header(name = "location", description = "Location to access the saved store.")),
                    @ApiResponse(responseCode = "400", description = "When store already contains identifier.")
            })
    ResponseEntity<Void> create(@RequestBody @Valid StoreDTO storeDTO);

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Updates a store.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully completes the request.",
                            headers = @Header(name = "location", description = "Location to access the saved store.")),
                    @ApiResponse(responseCode = "400", description = "When store doesn't contains identifier."),
                    @ApiResponse(responseCode = "404", description = "When store is not found.")
            })
    ResponseEntity<Void> update(@RequestBody @Valid StoreDTO storeDTO);

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Gets a store by its identifier.",
            responses = @ApiResponse(responseCode = "200", description = "Successfully completes the request."))
    ResponseEntity<StoreDTO> getById(@PathVariable long id);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Finds all stores with pagination.",
            responses = @ApiResponse(responseCode = "200", description = "Successfully completes the request.")
    )
    ResponseEntity<Page<StoreDTO>> findAll(@RequestParam int pageNumber, @RequestParam int pageSize);

}
