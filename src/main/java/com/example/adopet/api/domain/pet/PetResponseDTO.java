package com.example.adopet.api.domain.pet;

public record PetResponseDTO(Long id, String nome, String descricao, String idade, Boolean adotado) {
    public PetResponseDTO(Pet pet) {
        this(pet.getId(), pet.getNome(), pet.getDescricao(), pet.getIdade(), pet.getAdotado());
    }
}