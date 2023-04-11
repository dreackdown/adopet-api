package com.example.adopet.api.dto.Tutor;

import com.example.adopet.api.entities.Tutor;

public record DadosDetalhamentoTutor(Long id, String nome, String email) {

    public DadosDetalhamentoTutor(Tutor tutor) {
        this(tutor.getId(), tutor.getNome(), tutor.getEmail());
    }
}
