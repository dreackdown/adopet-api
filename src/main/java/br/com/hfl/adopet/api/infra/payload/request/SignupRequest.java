package br.com.hfl.adopet.api.infra.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record SignupRequest(
        @NotBlank
        @Size(min = 3, max = 20)
        String login,

        @NotBlank
        @Size(max = 50)
        @Email
        String email,

        Set<String> perfil,

        @NotBlank
        @Size(min = 6, max = 40)
        String senha) {
}