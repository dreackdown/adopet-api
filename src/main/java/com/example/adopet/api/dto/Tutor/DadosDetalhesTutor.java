package com.example.adopet.api.dto.Tutor;

import com.example.adopet.api.entities.Tutor;

public record DadosDetalhesTutor(Long id, String nome, String email) {

    public DadosDetalhesTutor(Tutor tutor) {
        this(tutor.getId(), tutor.getNome(), tutor.getEmail());
    }

}
