package com.moretto.bruno.myspringapi.controller;

import com.moretto.bruno.myspringapi.dto.ProductGroupDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/product-groups")
@Tag(name = "Product Groups", description = "Represents product groups endpoints.")
public interface ProductGroupController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Creates a new product group.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully completes the request.",
                            headers = @Header(name = "location", description = "Location to access the saved product group.")),
                    @ApiResponse(responseCode = "400", description = "When product group already contains identifier.")
            })
    ResponseEntity<Void> create(@RequestBody @Valid ProductGroupDTO productGroupDTO);

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Updates a product group.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully completes the request.",
                            headers = @Header(name = "location", description = "Location to access the saved product group.")),
                    @ApiResponse(responseCode = "400", description = "When product group doesn't contains identifier."),
                    @ApiResponse(responseCode = "404", description = "When product group is not found.")
            })
    ResponseEntity<Void> update(@RequestBody @Valid ProductGroupDTO productGroupDTO);

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Gets a product group by its identifier.",
            responses = @ApiResponse(responseCode = "200", description = "Successfully completes the request."))
    ResponseEntity<ProductGroupDTO> getById(@PathVariable long id);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Finds all product groups with pagination.",
            responses = @ApiResponse(responseCode = "200", description = "Successfully completes the request.")
    )
    ResponseEntity<Page<ProductGroupDTO>> findAll(@RequestParam int pageNumber, @RequestParam int pageSize);

}
