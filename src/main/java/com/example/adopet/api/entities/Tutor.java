package com.example.adopet.api.entities;

import com.example.adopet.api.dto.Tutor.DadosAtualizacaoTutor;
import com.example.adopet.api.dto.Tutor.DadosCadastroTutor;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Table(name = "tbl_tutor")
@Entity(name = "Tutor")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Tutor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String telefone;

    private String cidade;

    private String sobre;

    private String email;

    private String senha;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "image_url")
    private String imageUrl;

    public Tutor(DadosCadastroTutor dados) {
        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.cidade = dados.cidade();
        this.sobre = dados.sobre();
        this.email = dados.email();
        this.senha = dados.senha();
        this.createdAt = Instant.now();
    }

    public void update(DadosAtualizacaoTutor dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }

        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }

        if (dados.cidade() != null) {
            this.cidade = dados.cidade();
        }

        if (dados.sobre() != null) {
            this.sobre = dados.sobre();
        }

        if (dados.imageUrl() != null) {
            this.imageUrl = dados.imageUrl();
        }

        this.updatedAt = Instant.now();
    }
}
