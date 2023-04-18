package com.example.adopet.api.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Object id) {
        super("Nenhum registro encontrado com o Id: " + id);
    }
}
