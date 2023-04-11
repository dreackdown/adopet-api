package com.example.adopet.api.dto.Abrigo;

import com.example.adopet.api.dto.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroAbrigo(
        @NotBlank
        String nome,

        @NotNull @Valid DadosEndereco endereco) {
}
