package com.example.adopet.api.infra.payload.response;

import com.example.adopet.api.domain.adocao.Adocao;

import java.time.LocalDate;

public record AdocaoResponse(Long id, LocalDate data, String nomeTutor, String nomePet) {
    public AdocaoResponse(Adocao adocao) {
        this(adocao.getId(), adocao.getData(), adocao.getTutor().getNome(), adocao.getPet().getNome());
    }
}