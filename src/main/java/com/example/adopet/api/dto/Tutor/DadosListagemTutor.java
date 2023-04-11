package com.example.adopet.api.dto.Tutor;

import com.example.adopet.api.entities.Tutor;

public record DadosListagemTutor(Long id, String nome, String email) {

    public DadosListagemTutor(Tutor tutor) {
        this(tutor.getId(), tutor.getNome(), tutor.getEmail());
    }
}
