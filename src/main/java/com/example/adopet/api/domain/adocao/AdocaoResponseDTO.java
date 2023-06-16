package com.example.adopet.api.domain.adocao;

import java.time.LocalDate;

public record AdocaoResponseDTO(Long id, LocalDate data, String nomeTutor, String nomePet) {
    public AdocaoResponseDTO(Adocao adocao) {
        this(adocao.getId(), adocao.getData(), adocao.getTutor().getNome(), adocao.getPet().getNome());
    }
}