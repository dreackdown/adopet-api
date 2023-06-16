package com.example.adopet.api.infra.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Object id) {
        super("Nenhum registro encontrado com o Id: " + id);
    }
}