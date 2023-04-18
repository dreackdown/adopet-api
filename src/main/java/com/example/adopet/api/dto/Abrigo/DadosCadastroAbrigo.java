package com.example.adopet.api.dto.Abrigo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroAbrigo(
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank String telefone) {
}
