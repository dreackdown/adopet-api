package com.example.adopet.api.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Table(name = "tbl_pet")
@Entity(name = "Pet")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String nome;
    Integer idade;
    String porte;
    String temperamento;
    private Boolean adotado = false;
    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tbl_abrigo", referencedColumnName = "id")
    private Abrigo abrigo;
}
