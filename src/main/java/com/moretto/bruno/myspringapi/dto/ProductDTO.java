package com.moretto.bruno.myspringapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;

    @NotBlank(message = "Product name is required.")
    private String name;

    @NotBlank(message = "Product price is required.")
    @PositiveOrZero(message = "Product price must be a positive number.")
    private Double price;

    private Integer quantity;

    private ProductGroupDTO group;

}
