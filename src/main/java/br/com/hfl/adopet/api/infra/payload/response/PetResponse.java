package br.com.hfl.adopet.api.infra.payload.response;

import br.com.hfl.adopet.api.domain.pet.Pet;

public record PetResponse(Long id, String nome, String descricao, String idade, Boolean adotado) {
    public PetResponse(Pet pet) {
        this(pet.getId(), pet.getNome(), pet.getDescricao(), pet.getIdade(), pet.getAdotado());
    }
}