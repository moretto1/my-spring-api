package com.moretto.bruno.store.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ErrorResponse {

    private List<String> errors;
    private LocalDateTime dateTime;

    public ErrorResponse(List<String> errors) {
        this.errors = errors;
        this.dateTime = LocalDateTime.now();
    }

}
