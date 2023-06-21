package com.example.adopet.api.domain.tutor;

import com.example.adopet.api.domain.usuario.Usuario;
import com.example.adopet.api.infra.payload.request.TutorRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "nome", "email"})
@NoArgsConstructor
@Entity
@Table(name = "tbl_tutores")
public class Tutor extends Usuario {

    private String nome;

    public Tutor(TutorRequest request, String senhaBcrypt) {
        this.nome = request.nome();
        this.email = request.email();
        this.login = request.login();
        this.senha = senhaBcrypt;
    }
}