package com.example.adopet.api.dto.Abrigo;


import com.example.adopet.api.entities.Abrigo;

public record DadosListagemAbrigo(Long id, String nome) {

    public DadosListagemAbrigo(Abrigo abrigo) {
        this(abrigo.getId(), abrigo.getNome());
    }
}
