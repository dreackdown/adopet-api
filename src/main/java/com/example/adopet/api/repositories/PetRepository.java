package com.example.adopet.api.repositories;

import com.example.adopet.api.entities.Pet;
import com.example.adopet.api.entities.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
