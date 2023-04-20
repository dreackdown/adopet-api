package com.example.adopet.api.dto.Adocao;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAdocao(
        Long idPet,

        @NotNull
        Long idTutor,

        @NotNull
        LocalDateTime data) {
}
