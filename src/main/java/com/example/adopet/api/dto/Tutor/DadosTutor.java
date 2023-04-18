package com.example.adopet.api.dto.Tutor;

import com.example.adopet.api.entities.Tutor;

public record DadosTutor(Long id, String nome) {

    public DadosTutor(Tutor tutor) {
        this(tutor.getId(), tutor.getNome());
    }
}
