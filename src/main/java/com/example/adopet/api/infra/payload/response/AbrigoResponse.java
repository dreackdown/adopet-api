package com.example.adopet.api.infra.payload.response;

import com.example.adopet.api.domain.abrigo.Abrigo;

public record AbrigoResponse(Long id, String nome, String telefone, String email) {
    public AbrigoResponse(Abrigo abrigo) {
        this(abrigo.getId(), abrigo.getNome(), abrigo.getTelefone(), abrigo.getEmail());
    }
}