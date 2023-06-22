package br.com.hfl.adopet.api.infra.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PetRequest(
        @NotBlank String nome,
        @NotBlank String idade,
        @NotBlank String imagem,
        @NotBlank String descricao,
        @NotNull Boolean adotado,
        @NotNull Long idAbrigo) {
}