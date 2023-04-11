package com.example.adopet.api.dto.Abrigo;

import com.example.adopet.api.dto.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoAbrigo(@NotNull
                                     Long id,
                                     String nome,
                                     DadosEndereco endereco) {
}
