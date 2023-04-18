package com.example.adopet.api.dto.Abrigo;

import com.example.adopet.api.entities.Abrigo;

public record DadosDetalhesAbrigo(Long id, String nome, String telefone, String email) {

    public DadosDetalhesAbrigo(Abrigo abrigo) {
        this(abrigo.getId(), abrigo.getNome(), abrigo.getTelefone(), abrigo.getEmail());
    }
}
