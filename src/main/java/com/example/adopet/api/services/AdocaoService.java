package com.example.adopet.api.services;

import com.example.adopet.api.dto.Abrigo.DadosCadastroAbrigo;
import com.example.adopet.api.dto.Abrigo.DadosDetalhesAbrigo;
import com.example.adopet.api.dto.Adocao.DadosAdocao;
import com.example.adopet.api.dto.Adocao.DadosDetalhesAdocao;
import com.example.adopet.api.entities.Adocao;
import com.example.adopet.api.exceptions.ValidacaoException;
import com.example.adopet.api.repositories.AdocaoRepository;
import com.example.adopet.api.repositories.PetRepository;
import com.example.adopet.api.repositories.TutorRepository;
import com.example.adopet.api.services.exceptions.DatabaseException;
import com.example.adopet.api.services.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdocaoService {

    private final AdocaoRepository adocaoRepository;

    private final TutorRepository tutorRepository;

    private final PetRepository petRepository;

    public AdocaoService(AdocaoRepository adocaoRepository, TutorRepository tutorRepository, PetRepository petRepository) {
        this.adocaoRepository = adocaoRepository;
        this.tutorRepository = tutorRepository;
        this.petRepository = petRepository;
    }

    @Transactional
    public DadosDetalhesAdocao adotar(DadosAdocao dados) {
        log.info("Criando uma nova adocao...");

        if (!petRepository.existsById(dados.idPet())) {
            throw new ValidacaoException("Id do pet informado não existe!");
        }

        if (!tutorRepository.existsById(dados.idTutor())) {
            throw new ValidacaoException("Id do tutor informado não existe!");
        }

        var pet = petRepository.getReferenceById(dados.idPet());
        pet.setAdotado(true);

        var tutor = tutorRepository.getReferenceById(dados.idTutor());


        var adocao = new Adocao(null, pet, tutor, dados.data());
        adocaoRepository.save(adocao);

        log.info("Adocao criada com sucesso: " + adocao);
        return new DadosDetalhesAdocao(adocao);
    }

    public void deleteById(Long id) {
        try {
            adocaoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
