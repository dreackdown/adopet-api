package com.example.adopet.api.dto.Abrigo;


import com.example.adopet.api.entities.Abrigo;

public record DadosAbrigo(Long id, String nome) {

    public DadosAbrigo(Abrigo abrigo) {
        this(abrigo.getId(), abrigo.getNome());
    }
}
