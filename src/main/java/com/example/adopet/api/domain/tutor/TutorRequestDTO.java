package com.example.adopet.api.domain.tutor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record TutorRequestDTO(
        @NotBlank String nome,
        @NotBlank String login,
        @NotBlank @Email String email,
        @NotBlank String senha,
        @NotBlank String confirmacaoSenha) {
}