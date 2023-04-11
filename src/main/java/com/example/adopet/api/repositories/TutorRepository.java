package com.example.adopet.api.repositories;

import com.example.adopet.api.entities.Tutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
}
