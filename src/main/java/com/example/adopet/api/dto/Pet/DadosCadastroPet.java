package com.example.adopet.api.dto.Pet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroPet(
        @NotBlank String nome,
        @NotBlank String idade,
        @NotBlank String imagem,
        @NotBlank String descricao,
        @NotNull Boolean adotado,
        @NotNull Long idAbrigo) {
}
