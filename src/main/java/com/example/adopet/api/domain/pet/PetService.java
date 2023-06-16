package com.example.adopet.api.domain.pet;

import com.example.adopet.api.domain.abrigo.Abrigo;
import com.example.adopet.api.domain.abrigo.AbrigoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService {

    private final AbrigoRepository abrigoRepository;
    private final PetRepository petRepository;

    public PetService(AbrigoRepository abrigoRepository, PetRepository petRepository) {
        this.abrigoRepository = abrigoRepository;
        this.petRepository = petRepository;
    }

    @Transactional
    public Pet save(PetRequestDTO request) {
        var abrigo = abrigoRepository.getReferenceById(request.idAbrigo());

        var pet = new Pet(request, abrigo);
        return petRepository.save(pet);
    }

    public PetResponseDTO findById(Long id) {
        try {
            var pet = petRepository.getReferenceById(id);
            return new PetResponseDTO(pet);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException();
        }
    }

    public List<PetResponseDTO> findAll() {
        var pets = petRepository.findAll();

        if (pets.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return pets.stream().map(PetResponseDTO::new).collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        if (petRepository.existsById(id)) {
            petRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Transactional
    public PetResponseDTO update(PetUpdateDTO request) {
        try {
            Abrigo abrigo = null;
            if (request.idAbrigo() != null) {
                abrigo = abrigoRepository.getReferenceById(request.idAbrigo());
            }
            var pet = petRepository.getReferenceById(request.id());
            updateData(pet, request, abrigo);
            return new PetResponseDTO(pet);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException();
        }
    }

    private void updateData(Pet entity, PetUpdateDTO obj, Abrigo abrigo) {
        if (obj.nome() != null) {
            entity.setNome(obj.nome());
        }
        if (obj.descricao() != null) {
            entity.setDescricao(obj.descricao());
        }
        if (obj.adotado() != null) {
            entity.setAdotado(obj.adotado());
        }
        if (obj.imagem() != null) {
            entity.setImagem(obj.imagem());
        }
        if (abrigo != null) {
            entity.setAbrigo(abrigo);
        }
    }
}