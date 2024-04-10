package com.moretto.bruno.store.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreDTO {

    private Long id;

    @NotBlank(message = "Store name is required.")
    private String name;

    @NotBlank(message = "Store CNPJ is required.")
    @CNPJ
    private String cnpj;

    @NotBlank(message = "Store email is required.")
    @Email(message = "Store email has an invalid format.")
    private String email;

}
