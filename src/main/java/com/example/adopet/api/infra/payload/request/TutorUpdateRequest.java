package com.example.adopet.api.infra.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record TutorUpdateRequest(
        @NotNull
        Long id,
        String nome,
        @Email String email,
        String senha,
        String confirmacaoSenha) {
}