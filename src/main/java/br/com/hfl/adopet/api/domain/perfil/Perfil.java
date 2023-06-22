package br.com.hfl.adopet.api.domain.perfil;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "nome"})
@Entity
@Table(name = "tbl_perfis")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EPerfil nome;

    public Perfil(EPerfil nome) {
        this.nome = nome;
    }
}