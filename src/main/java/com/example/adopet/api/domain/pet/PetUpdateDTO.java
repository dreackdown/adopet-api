package com.example.adopet.api.domain.pet;

public record PetUpdateDTO(Long id, String nome, String descricao, String idade, Boolean adotado, String imagem, Long idAbrigo) {
}