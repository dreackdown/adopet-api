package com.example.adopet.api.dto.Pet;

public record DadosAtualizacaoPet(Long id, String nome, String descricao, String idade, Boolean adotado, String imagem, Long idAbrigo) {
}