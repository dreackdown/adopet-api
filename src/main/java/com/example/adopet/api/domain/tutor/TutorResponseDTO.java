package com.example.adopet.api.domain.tutor;

public record TutorResponseDTO(Long id, String nome, String email) {
    public TutorResponseDTO(Tutor tutor) {
        this(tutor.getId(), tutor.getNome(), tutor.getEmail());
    }
}