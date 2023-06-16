package com.example.adopet.api.domain.adocao;

import java.time.LocalDate;

public record AdocaoRequestDTO(Long petId, Long tutorId, LocalDate data) {
}