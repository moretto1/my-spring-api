package com.moretto.bruno.myspringapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductGroupDTO {

    private Long id;

    @NotBlank(message = "Product group name is required.")
    private String name;

}
