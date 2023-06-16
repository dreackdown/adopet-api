package com.example.adopet.api.domain.abrigo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AbrigoRequestDTO(
        @NotBlank String nome,

        @NotBlank String login,

        @NotBlank String senha,
        @NotBlank @Email String email,
        @NotBlank String telefone) {
}