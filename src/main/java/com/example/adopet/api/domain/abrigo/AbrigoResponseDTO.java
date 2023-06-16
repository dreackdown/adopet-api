package com.example.adopet.api.domain.abrigo;

public record AbrigoResponseDTO(Long id, String nome, String telefone, String email) {
    public AbrigoResponseDTO(Abrigo abrigo) {
        this(abrigo.getId(), abrigo.getNome(), abrigo.getTelefone(), abrigo.getEmail());
    }
}