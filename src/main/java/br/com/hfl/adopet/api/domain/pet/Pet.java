package br.com.hfl.adopet.api.domain.pet;

import br.com.hfl.adopet.api.domain.abrigo.Abrigo;
import br.com.hfl.adopet.api.infra.payload.request.PetRequest;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "nome", "descricao"})
@NoArgsConstructor
@Entity
@Table(name = "tbl_pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private Boolean adotado;
    private String imagem;
    private String idade;

    @ManyToOne
    @JoinColumn(name = "abrigo_id")
    private Abrigo abrigo;

    public Pet(PetRequest dados, Abrigo abrigo) {
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.adotado = dados.adotado();
        this.imagem = dados.imagem();
        this.idade = dados.idade();
        this.abrigo = abrigo;
    }

    public void marcarComoAdotado() {
        this.adotado = true;
    }
}
