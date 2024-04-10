package com.moretto.bruno.myspringapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EntityNotFoundException extends ResponseStatusException {

    public EntityNotFoundException(String presentableName, long id) {
        super(HttpStatus.NOT_FOUND, String.format("%s with identifier %d doesn't exists.", presentableName, id));
    }

}
