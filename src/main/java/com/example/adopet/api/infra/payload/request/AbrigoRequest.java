package com.example.adopet.api.infra.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AbrigoRequest(
        @NotBlank String nome,

        @NotBlank String login,

        @NotBlank String senha,
        @NotBlank @Email String email,
        @NotBlank String telefone) {
}