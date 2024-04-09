package com.moretto.bruno.myspringapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EntityWithoutIdException extends ResponseStatusException {

    public EntityWithoutIdException(String presentableName) {
        super(HttpStatus.BAD_REQUEST, String.format("%s without identifier.", presentableName));
    }

}
