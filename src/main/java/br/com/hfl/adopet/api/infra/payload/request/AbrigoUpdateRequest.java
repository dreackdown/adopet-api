package br.com.hfl.adopet.api.infra.payload.request;

public record AbrigoUpdateRequest(Long id, String nome, String email, String telefone) {
}