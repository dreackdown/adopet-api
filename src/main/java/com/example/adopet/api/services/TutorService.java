package com.example.adopet.api.services;

import com.example.adopet.api.dto.Tutor.*;
import com.example.adopet.api.entities.Tutor;
import com.example.adopet.api.services.exceptions.DatabaseException;
import com.example.adopet.api.services.exceptions.ResourceNotFoundException;
import com.example.adopet.api.repositories.TutorRepository;
import com.example.adopet.api.services.exceptions.ResourceEmptyException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TutorService {

    private final TutorRepository tutorRepository;

    public TutorService(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    @Transactional
    public Tutor save(DadosCadastroTutor dados) {
        log.info("Criando um novo tutor...");

        var tutor = new Tutor(dados);

        log.info("Tutor criado com sucesso: " + tutor);
        return tutorRepository.save(tutor);
    }

    public List<DadosDetalhesTutor> findAll() {
        var tutores = tutorRepository.findAll();

        if (tutores.isEmpty()) {
            throw new ResourceEmptyException("Não encontrado.");
        }

        return tutores.stream().map(DadosDetalhesTutor::new).collect(Collectors.toList());
    }

    public DadosDetalhesTutor findById(Long id) {
        try {
            var tutor = tutorRepository.getReferenceById(id);
            return new DadosDetalhesTutor(tutor);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Nenhum registro encontrado.");
        }
    }

    public void deleteById(Long id) {
        try {
            tutorRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Transactional
    public DadosTutor update(DadosAtualizacaoTutor dados) {
        try {
            var tutor = tutorRepository.getReferenceById(dados.id());
            tutor.update(dados);
            return new DadosTutor(tutor);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(dados.id());
        }
    }
}
