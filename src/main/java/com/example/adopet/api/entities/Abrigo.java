package com.example.adopet.api.entities;

import com.example.adopet.api.dto.Abrigo.DadosAtualizacaoAbrigo;
import com.example.adopet.api.dto.Abrigo.DadosCadastroAbrigo;
import jakarta.persistence.*;
import lombok.*;

@Getter
@ToString(of = {"id", "nome", "telefone", "email"})
@NoArgsConstructor
@Entity
@Table(name = "tbl_abrigos")
public class Abrigo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String email;

    public Abrigo(DadosCadastroAbrigo dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
    }

    public void update(DadosAtualizacaoAbrigo dados) {
        this.nome = dados.nome() != null ? dados.nome() : this.nome;
        this.email = dados.email() != null ? dados.email() : this.email;
        this.telefone = dados.telefone() != null ? dados.telefone() : this.telefone;
    }

}

