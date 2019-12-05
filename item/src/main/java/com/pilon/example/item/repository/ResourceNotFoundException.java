package com.pilon.example.item.repository;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {

    private static final long serialVersionUID = 7775403028017282066L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}