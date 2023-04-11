package com.example.adopet.api.services;

import com.example.adopet.api.dto.Abrigo.DadosAtualizacaoAbrigo;
import com.example.adopet.api.dto.Abrigo.DadosCadastroAbrigo;
import com.example.adopet.api.dto.Abrigo.DadosDetalhamentoAbrigo;
import com.example.adopet.api.dto.Abrigo.DadosListagemAbrigo;
import com.example.adopet.api.dto.Tutor.DadosAtualizacaoTutor;
import com.example.adopet.api.dto.Tutor.DadosCadastroTutor;
import com.example.adopet.api.dto.Tutor.DadosDetalhamentoTutor;
import com.example.adopet.api.entities.Abrigo;
import com.example.adopet.api.entities.Tutor;
import com.example.adopet.api.repositories.AbrigoRepository;
import com.example.adopet.api.services.exceptions.ResourceEmptyException;
import com.example.adopet.api.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
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

    public List<DadosListagemAbrigo> findAll() {
        List<DadosListagemAbrigo> abrigos = abrigoRepository.findAll().stream().map(DadosListagemAbrigo::new).toList();

        if (abrigos.isEmpty()) {
            throw new ResourceEmptyException("Nenhum registro encontrado.");
        }

        return abrigos;
    }

    public DadosDetalhamentoAbrigo findById(Long id) {
        var abrigo = abrigoRepository.getReferenceById(id);
        return new DadosDetalhamentoAbrigo(abrigo);
    }

    @Transactional
    public Abrigo save(DadosCadastroAbrigo dados) {
        log.info("Criando um novo abrigo {}", dados.nome());

        var abrigo = new Abrigo(dados);

        return abrigoRepository.save(abrigo);
    }

    public void deleteById(Long id) {
        try {
            abrigoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    @Transactional
    public DadosDetalhamentoAbrigo update(DadosAtualizacaoAbrigo dados) {
        try {

            var abrigo = abrigoRepository.getReferenceById(dados.id());
            abrigo.update(dados);
            return new DadosDetalhamentoAbrigo(abrigo);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(dados.id());
        }
    }
}
