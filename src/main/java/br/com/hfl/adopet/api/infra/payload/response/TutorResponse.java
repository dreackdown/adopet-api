package br.com.hfl.adopet.api.infra.payload.response;

import br.com.hfl.adopet.api.domain.tutor.Tutor;

public record TutorResponse(Long id, String nome, String email) {
    public TutorResponse(Tutor tutor) {
        this(tutor.getId(), tutor.getNome(), tutor.getEmail());
    }
}