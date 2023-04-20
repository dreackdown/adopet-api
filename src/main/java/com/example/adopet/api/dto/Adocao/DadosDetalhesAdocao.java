package com.example.adopet.api.dto.Adocao;

import com.example.adopet.api.entities.Adocao;

import java.time.LocalDateTime;

public record DadosDetalhesAdocao(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {
    public DadosDetalhesAdocao(Adocao adocao) {
        this(adocao.getId(), adocao.getPet().getId(), adocao.getTutor().getId(), adocao.getData());
    }
}
