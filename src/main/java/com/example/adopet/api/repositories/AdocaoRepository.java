package com.example.adopet.api.repositories;

import com.example.adopet.api.entities.Abrigo;
import com.example.adopet.api.entities.Adocao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdocaoRepository extends JpaRepository<Adocao, Long> {
}
