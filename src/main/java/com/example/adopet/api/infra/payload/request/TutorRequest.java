package com.example.adopet.api.infra.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record TutorRequest(
        @NotBlank String nome,
        @NotBlank String login,
        @NotBlank @Email String email,
        @NotBlank String senha,
        @NotBlank String confirmacaoSenha) {
}