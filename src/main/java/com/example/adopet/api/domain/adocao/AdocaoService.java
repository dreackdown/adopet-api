package com.example.adopet.api.domain.adocao;

import com.example.adopet.api.domain.pet.PetRepository;
import com.example.adopet.api.domain.tutor.TutorRepository;
import com.example.adopet.api.infra.payload.request.AdocaoRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AdocaoService {

    private final PetRepository petRepository;

    private final TutorRepository tutorRepository;

    private final AdocaoRepository adocaoRepository;

    public AdocaoService(PetRepository petRepository, TutorRepository tutorRepository, AdocaoRepository adocaoRepository) {
        this.petRepository = petRepository;
        this.tutorRepository = tutorRepository;
        this.adocaoRepository = adocaoRepository;
    }

    @Transactional
    public Adocao save(AdocaoRequest request) {
        var pet = petRepository.findById(request.petId()).orElseThrow(() -> new IllegalArgumentException("Pet não cadastrado!"));
        if (pet.getAdotado()) {
            throw new IllegalArgumentException("Pet já adotado!");
        }

        var tutor = tutorRepository.findById(request.tutorId()).orElseThrow(() -> new IllegalArgumentException("tutor não cadastrado!"));

        var adocao = new Adocao(request, pet, tutor);
        adocaoRepository.save(adocao);

        pet.marcarComoAdotado();

        return adocao;
    }

    public void deleteById(Long id) {
        var adocao = adocaoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Adoção não cadastrada!"));
        adocaoRepository.deleteById(id);
    }
}