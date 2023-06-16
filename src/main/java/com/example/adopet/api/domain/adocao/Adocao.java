package com.example.adopet.api.domain.adocao;

import com.example.adopet.api.domain.pet.Pet;
import com.example.adopet.api.domain.tutor.Tutor;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "data"})
@NoArgsConstructor
@Entity
@Table(name = "tbl_adocoes")
public class Adocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data;

    @OneToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    public Adocao(AdocaoRequestDTO request, Pet pet, Tutor tutor) {
        this.data = request.data();
        this.pet = pet;
        this.tutor = tutor;
    }
}