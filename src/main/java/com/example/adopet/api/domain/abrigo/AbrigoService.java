package com.example.adopet.api.domain.abrigo;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AbrigoService {

    private final AbrigoRepository abrigoRepository;

    public AbrigoService(AbrigoRepository abrigoRepository) {
        this.abrigoRepository = abrigoRepository;
    }

    @Transactional
    public Abrigo save(AbrigoRequestDTO request) {
        var abrigo = new Abrigo(request);
        return abrigoRepository.save(abrigo);
    }

    public AbrigoResponseDTO findById(Long id) {
        try {
            var abrigo = abrigoRepository.getReferenceById(id);
            return new AbrigoResponseDTO(abrigo);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException();
        }
    }

    public List<AbrigoResponseDTO> findAll() {
        var abrigos = abrigoRepository.findAll();

        if (abrigos.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return abrigos.stream().map(AbrigoResponseDTO::new).collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        if (abrigoRepository.existsById(id)) {
            abrigoRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Transactional
    public AbrigoResponseDTO update(AbrigoUpdateDTO request) {
        try {
            var abrigo = abrigoRepository.getReferenceById(request.id());
            updateData(abrigo, request);
            return new AbrigoResponseDTO(abrigo);

        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException();
        }
    }

    private void updateData(Abrigo entity, AbrigoUpdateDTO obj) {
        if (obj.nome() != null) {
            entity.setNome(obj.nome());
        }
        if (obj.email() != null) {
            entity.setEmail(obj.email());
        }
        if (obj.telefone() != null) {
            entity.setTelefone(obj.telefone());
        }
    }
}