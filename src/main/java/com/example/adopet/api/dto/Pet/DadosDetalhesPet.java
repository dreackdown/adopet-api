package com.example.adopet.api.dto.Pet;

import com.example.adopet.api.entities.Pet;

public record DadosDetalhesPet(Long id, String nome, String descricao, String idade, Boolean adotado) {

    public DadosDetalhesPet(Pet pet) {
        this(pet.getId(), pet.getNome(), pet.getDescricao(), pet.getIdade(), pet.getAdotado());
    }

}
