package com.example.adopet.api.dto.Tutor;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoTutor(
        @NotNull
        Long id,
        String nome,
        String telefone,
        String cidade,
        String sobre,
        String imageUrl
) {
}
