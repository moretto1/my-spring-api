package com.moretto.bruno.store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EntityAlreadyExistsException extends ResponseStatusException {

    public EntityAlreadyExistsException(String presentableName) {
        super(HttpStatus.BAD_REQUEST, String.format("%s already exists.", presentableName));
    }

}
