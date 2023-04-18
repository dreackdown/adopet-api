package com.example.adopet.api.services;

import com.example.adopet.api.dto.Abrigo.DadosAbrigo;
import com.example.adopet.api.dto.Abrigo.DadosAtualizacaoAbrigo;
import com.example.adopet.api.dto.Abrigo.DadosDetalhesAbrigo;
import com.example.adopet.api.dto.Pet.DadosAtualizacaoPet;
import com.example.adopet.api.dto.Pet.DadosCadastroPet;
import com.example.adopet.api.dto.Pet.DadosDetalhesPet;
import com.example.adopet.api.dto.Pet.DadosPet;
import com.example.adopet.api.dto.Tutor.DadosCadastroTutor;
import com.example.adopet.api.dto.Tutor.DadosDetalhesTutor;
import com.example.adopet.api.entities.Abrigo;
import com.example.adopet.api.entities.Pet;
import com.example.adopet.api.entities.Tutor;
import com.example.adopet.api.repositories.AbrigoRepository;
import com.example.adopet.api.repositories.PetRepository;
import com.example.adopet.api.services.exceptions.DatabaseException;
import com.example.adopet.api.services.exceptions.ResourceEmptyException;
import com.example.adopet.api.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PetService {

    private final AbrigoRepository abrigoRepository;

    private final PetRepository petRepository;

    public PetService(AbrigoRepository abrigoRepository, PetRepository petRepository) {
        this.abrigoRepository = abrigoRepository;
        this.petRepository = petRepository;
    }

    @Transactional
    public Pet save(DadosCadastroPet dados) {
        log.info("Criando um novo pet...");

        var abrigo = abrigoRepository.getReferenceById(dados.idAbrigo());

        var pet = new Pet(dados, abrigo);

        log.info("Pet criado com sucesso: " + pet);
        return petRepository.save(pet);
    }

    public List<DadosDetalhesPet> findAll() {
        var pets = petRepository.findAll();

        if (pets.isEmpty()) {
            throw new ResourceEmptyException("Não encontrado.");
        }

        return pets.stream().map(DadosDetalhesPet::new).collect(Collectors.toList());
    }

    public DadosDetalhesPet findById(Long id) {
        try {
            var pet = petRepository.getReferenceById(id);
            return new DadosDetalhesPet(pet);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Nenhum registro encontrado.");
        }
    }

    public void deleteById(Long id) {
        try {
            petRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Transactional
    public DadosPet update(DadosAtualizacaoPet dados) {
        try {
            Abrigo abrigo = null;
            if (dados.idAbrigo() != null) {
                abrigo = abrigoRepository.getReferenceById(dados.idAbrigo());
            }
            var pet = petRepository.getReferenceById(dados.id());
            pet.update(dados, abrigo);

            return new DadosPet(pet);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(dados.id());
        }
    }
}
