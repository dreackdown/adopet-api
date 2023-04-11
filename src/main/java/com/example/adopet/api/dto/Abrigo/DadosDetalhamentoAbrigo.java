package com.example.adopet.api.dto.Abrigo;

import com.example.adopet.api.entities.Abrigo;
import com.example.adopet.api.entities.Endereco;

public record DadosDetalhamentoAbrigo(Long id, String nome, Endereco endereco) {

    public DadosDetalhamentoAbrigo(Abrigo abrigo) {
        this(abrigo.getId(), abrigo.getNome(), abrigo.getEndereco());
    }
}
