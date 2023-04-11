package com.example.adopet.api.services;

import com.example.adopet.api.dto.Tutor.DadosAtualizacaoTutor;
import com.example.adopet.api.dto.Tutor.DadosCadastroTutor;
import com.example.adopet.api.dto.Tutor.DadosDetalhamentoTutor;
import com.example.adopet.api.dto.Tutor.DadosListagemTutor;
import com.example.adopet.api.entities.Tutor;
import com.example.adopet.api.services.exceptions.ResourceNotFoundException;
import com.example.adopet.api.repositories.TutorRepository;
import com.example.adopet.api.services.exceptions.ResourceEmptyException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TutorService {

    private final TutorRepository tutorRepository;

    public TutorService(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    public List<DadosListagemTutor> findAll() {
        List<DadosListagemTutor> tutores = tutorRepository.findAll().stream().map(DadosListagemTutor::new).toList();

        if (tutores.isEmpty()) {
            throw new ResourceEmptyException("Nenhum registro encontrado.");
        }

        return tutores;
    }

    public DadosDetalhamentoTutor findById(Long id) {
        var tutor = tutorRepository.getReferenceById(id);
        return new DadosDetalhamentoTutor(tutor);
    }

    @Transactional
    public Tutor save(DadosCadastroTutor dados) {
        log.info("Criando um novo tutor {}", dados.nome());

        var tutor = new Tutor(dados);

        return tutorRepository.save(tutor);
    }

    public void deleteById(Long id) {
        try {
            tutorRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    @Transactional
    public DadosDetalhamentoTutor update(DadosAtualizacaoTutor dados) {
        try {

            var tutor = tutorRepository.getReferenceById(dados.id());
            tutor.update(dados);
            return new DadosDetalhamentoTutor(tutor);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(dados.id());
        }
    }
}
