package com.github.Rharhuky.api.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Info Not Found :/")
public class InfoNotFoundException extends RuntimeException{

    public InfoNotFoundException(String message) {
        super(message);
    }

    public InfoNotFoundException() {
    }
}
