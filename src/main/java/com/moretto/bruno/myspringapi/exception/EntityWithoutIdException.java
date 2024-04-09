package com.moretto.bruno.myspringapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EntityWithoutIdException extends ResponseStatusException {

    public EntityWithoutIdException(String presentableName) {
        super(HttpStatus.NOT_FOUND, String.format("%s without identifier.", presentableName));
    }

}
