package com.example.adopet.api.dto.Pet;

import com.example.adopet.api.entities.Pet;

public record DadosPet(Long id, String nome) {

    public DadosPet(Pet pet) {
        this(pet.getId(), pet.getNome());
    }
}
