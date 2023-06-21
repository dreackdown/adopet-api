package com.example.adopet.api.infra.payload.request;

import java.time.LocalDate;

public record AdocaoRequest(Long petId, Long tutorId, LocalDate data) {
}