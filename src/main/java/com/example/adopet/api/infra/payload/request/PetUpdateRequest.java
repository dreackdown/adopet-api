package com.example.adopet.api.infra.payload.request;

public record PetUpdateRequest(Long id, String nome, String descricao, String idade, Boolean adotado, String imagem, Long idAbrigo) {
}