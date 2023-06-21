package com.example.adopet.api.infra.payload.response;

import com.example.adopet.api.domain.pet.Pet;

public record PetResponse(Long id, String nome, String descricao, String idade, Boolean adotado) {
    public PetResponse(Pet pet) {
        this(pet.getId(), pet.getNome(), pet.getDescricao(), pet.getIdade(), pet.getAdotado());
    }
}