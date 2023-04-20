package com.example.adopet.api.entities;

import com.example.adopet.api.dto.Pet.DadosAtualizacaoPet;
import com.example.adopet.api.dto.Pet.DadosCadastroPet;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@ToString(of = {"id", "nome", "descricao"})
@NoArgsConstructor
@Entity
@Table(name = "tbl_pets")
public class Pet {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter(AccessLevel.NONE)
    private String nome;

    @Setter(AccessLevel.NONE)
    private String descricao;

    private Boolean adotado;

    @Setter(AccessLevel.NONE)
    private String imagem;

    @Setter(AccessLevel.NONE)
    private String idade;

    @ManyToOne
    @JoinColumn(name = "abrigo_id")
    @Setter(AccessLevel.NONE)
    private Abrigo abrigo;

    public Pet(DadosCadastroPet dados, Abrigo abrigo) {
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.adotado = dados.adotado();
        this.imagem = dados.imagem();
        this.idade = dados.idade();
        this.abrigo = abrigo;
    }

    public void update(DadosAtualizacaoPet dados, Abrigo abrigo) {
        this.nome = dados.nome() != null ? dados.nome() : this.nome;
        this.descricao = dados.descricao() != null ? dados.descricao() : this.descricao;
        this.adotado = dados.adotado() != null ? dados.adotado() : this.adotado;
        this.imagem = dados.imagem() != null ? dados.imagem() : this.imagem;
        this.idade = dados.idade() != null ? dados.idade() : this.idade;
        this.abrigo = abrigo != null ? abrigo : this.abrigo;
    }
}
