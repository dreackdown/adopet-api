package com.example.adopet.api.domain.abrigo;

import com.example.adopet.api.domain.usuario.Usuario;
import com.example.adopet.api.infra.payload.request.AbrigoRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "nome", "email", "telefone"})
@NoArgsConstructor
@Entity
@Table(name = "tbl_abrigos")
public class Abrigo extends Usuario {

    private String nome;
    private String telefone;

    public Abrigo(AbrigoRequest request) {
        this.nome = request.nome();
        this.email = request.email();
        this.telefone = request.telefone();
        this.login = request.login();
        this.senha = request.senha();
    }
}