package com.moretto.bruno.myspringapi.controller;

import com.moretto.bruno.myspringapi.dto.ProductDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/products")
@Tag(name = "Products", description = "Represents products endpoints.")
public interface ProductController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Creates a new product.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully completes the request.",
                            headers = @Header(name = "location", description = "Location to access the saved product.")),
                    @ApiResponse(responseCode = "400", description = "When product already contains identifier.")
            })
    ResponseEntity<Void> create(@RequestBody @Valid ProductDTO productDTO);

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Updates a product.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully completes the request.",
                            headers = @Header(name = "location", description = "Location to access the saved product.")),
                    @ApiResponse(responseCode = "400", description = "When product doesn't contains identifier."),
                    @ApiResponse(responseCode = "404", description = "When product is not found.")
            })
    ResponseEntity<Void> update(@RequestBody @Valid ProductDTO productDTO);

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Gets a product by its identifier.",
            responses = @ApiResponse(responseCode = "200", description = "Successfully completes the request."))
    ResponseEntity<ProductDTO> getById(
            @PathVariable @Parameter(description = "Product identifier.", example = "123") long id
    );

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Finds all products with pagination.",
            responses = @ApiResponse(responseCode = "200", description = "Successfully completes the request.")
    )
    ResponseEntity<Page<ProductDTO>> findAll(
            @RequestParam @Parameter(description = "Number of the page to return.", example = "2") int pageNumber,
            @RequestParam @Parameter(description = "Size of the page to return.", example = "10") int pageSize
    );

}
