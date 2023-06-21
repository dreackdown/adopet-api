package com.example.adopet.api.domain.tutor;

import com.example.adopet.api.domain.perfil.EPerfil;
import com.example.adopet.api.domain.perfil.Perfil;
import com.example.adopet.api.domain.perfil.PerfilRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TutorService {

    private final TutorRepository tutorRepository;
    private final PerfilRepository perfilRepository;
    private final PasswordEncoder encoder;

    public TutorService(TutorRepository tutorRepository, PerfilRepository perfilRepository, PasswordEncoder encoder
    ) {
        this.tutorRepository = tutorRepository;
        this.perfilRepository = perfilRepository;
        this.encoder = encoder;
    }

    @Transactional
    public Tutor save(TutorRequestDTO request) {
        var tutor = new Tutor(request, encoder.encode(request.senha()));

        Set<Perfil> roles = new HashSet<>();

        Perfil perfilTutor = perfilRepository.findByNome(EPerfil.ROLE_TUTOR)
                .orElseThrow(() -> new RuntimeException("Erro: Perfil nao encontrado."));

        roles.add(perfilTutor);
        tutor.setPerfis(roles);
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