package com.example.adopet.api.domain.tutor;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TutorService {

    private final TutorRepository tutorRepository;

    private final PasswordEncoder encoder;

    public TutorService(TutorRepository tutorRepository, PasswordEncoder encoder) {
        this.tutorRepository = tutorRepository;
        this.encoder = encoder;
    }

    @Transactional
    public Tutor save(TutorRequestDTO request) {
        var tutor = new Tutor(request, encoder.encode(request.senha()));
        return tutorRepository.save(tutor);
    }

    public TutorResponseDTO findById(Long id) {
        try {
            var tutor = tutorRepository.getReferenceById(id);
            return new TutorResponseDTO(tutor);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException();
        }
    }

    public List<TutorResponseDTO> findAll() {
        var tutores = tutorRepository.findAll();

        if (tutores.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return tutores.stream().map(TutorResponseDTO::new).collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        if (tutorRepository.existsById(id)) {
            tutorRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Transactional
    public TutorResponseDTO update(TutorUpdateDTO request) {
        try {
            var tutor = tutorRepository.getReferenceById(request.id());
            updateData(tutor, request);
            return new TutorResponseDTO(tutor);

        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException();
        }
    }

    private void updateData(Tutor entity, TutorUpdateDTO obj) {
        if (obj.nome() != null) {
            entity.setNome(obj.nome());
        }
        if (obj.email() != null) {
            entity.setEmail(obj.email());
        }
    }
}