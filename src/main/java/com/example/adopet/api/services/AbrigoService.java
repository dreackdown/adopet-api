package com.example.adopet.api.services;

import com.example.adopet.api.dto.Abrigo.DadosAtualizacaoAbrigo;
import com.example.adopet.api.dto.Abrigo.DadosCadastroAbrigo;
import com.example.adopet.api.dto.Abrigo.DadosDetalhesAbrigo;
import com.example.adopet.api.dto.Abrigo.DadosAbrigo;
import com.example.adopet.api.dto.Tutor.DadosDetalhesTutor;
import com.example.adopet.api.dto.Tutor.DadosTutor;
import com.example.adopet.api.entities.Abrigo;
import com.example.adopet.api.repositories.AbrigoRepository;
import com.example.adopet.api.services.exceptions.DatabaseException;
import com.example.adopet.api.services.exceptions.ResourceEmptyException;
import com.example.adopet.api.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AbrigoService {

    private final AbrigoRepository abrigoRepository;

    public AbrigoService(AbrigoRepository abrigoRepository) {
        this.abrigoRepository = abrigoRepository;
    }

    @Transactional
    public Abrigo save(DadosCadastroAbrigo dados) {
        log.info("Criando um novo abrigo...");

        var abrigo = new Abrigo(dados);

        log.info("Abrigo criado com sucesso: " + abrigo);
        return abrigoRepository.save(abrigo);
    }

    public List<DadosDetalhesAbrigo> findAll() {
        var abrigos = abrigoRepository.findAll();

        if (abrigos.isEmpty()) {
            throw new ResourceEmptyException("Nenhum registro encontrado.");
        }

        return abrigos.stream().map(DadosDetalhesAbrigo::new).toList();
    }

    public DadosDetalhesAbrigo findById(Long id) {
        try {
            var abrigo = abrigoRepository.getReferenceById(id);
            return new DadosDetalhesAbrigo(abrigo);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Nenhum registro encontrado.");
        }
    }

    public void deleteById(Long id) {
        try {
            abrigoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Transactional
    public DadosAbrigo update(DadosAtualizacaoAbrigo dados) {
        try {
            var abrigo = abrigoRepository.getReferenceById(dados.id());
            abrigo.update(dados);
            return new DadosAbrigo(abrigo);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(dados.id());
        }
    }
}
