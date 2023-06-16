package com.example.adopet.api.domain.tutor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record TutorUpdateDTO(
        @NotNull
        Long id,
        String nome,
        @Email String email,
        String senha,
        String confirmacaoSenha) {
}