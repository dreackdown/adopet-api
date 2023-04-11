package com.example.adopet.api.entities;

import com.example.adopet.api.dto.Abrigo.DadosAtualizacaoAbrigo;
import com.example.adopet.api.dto.Abrigo.DadosCadastroAbrigo;
import com.example.adopet.api.dto.Abrigo.DadosDetalhamentoAbrigo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Table(name = "tbl_abrigo")
@Entity(name = "Abrigo")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Abrigo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Embedded
    private Endereco endereco;

    public Abrigo(DadosCadastroAbrigo dados) {
        this.nome = dados.nome();
        this.endereco = new Endereco(dados.endereco());
        this.createdAt = Instant.now();
    }

    public void update(DadosAtualizacaoAbrigo dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }

        if (dados.endereco() != null) {
            this.endereco.update(dados.endereco());
        }
    }
}

