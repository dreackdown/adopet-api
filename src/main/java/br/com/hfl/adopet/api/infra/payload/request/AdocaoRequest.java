package br.com.hfl.adopet.api.infra.payload.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AdocaoRequest(
        @NotNull Long petId,
        @NotNull
        Long tutorId,
        @NotNull
        LocalDate data) {
}