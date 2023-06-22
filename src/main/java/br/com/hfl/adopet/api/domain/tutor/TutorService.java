package br.com.hfl.adopet.api.domain.tutor;

import br.com.hfl.adopet.api.domain.perfil.EPerfil;
import br.com.hfl.adopet.api.domain.perfil.PerfilRepository;
import br.com.hfl.adopet.api.domain.perfil.Perfil;
import br.com.hfl.adopet.api.infra.payload.request.TutorRequest;
import br.com.hfl.adopet.api.infra.payload.request.TutorUpdateRequest;
import br.com.hfl.adopet.api.infra.payload.response.TutorResponse;
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
    public Tutor save(TutorRequest request) {
        var tutor = new Tutor(request, encoder.encode(request.senha()));

        Set<Perfil> roles = new HashSet<>();

        Perfil perfilTutor = perfilRepository.findByNome(EPerfil.ROLE_TUTOR)
                .orElseThrow(() -> new RuntimeException("Erro: Perfil nao encontrado."));

        roles.add(perfilTutor);
        tutor.setPerfis(roles);
        return tutorRepository.save(tutor);
    }

    public TutorResponse findById(Long id) {
        try {
            var tutor = tutorRepository.getReferenceById(id);
            return new TutorResponse(tutor);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException();
        }
    }

    public List<TutorResponse> findAll() {
        var tutores = tutorRepository.findAll();

        if (tutores.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return tutores.stream().map(TutorResponse::new).collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        if (tutorRepository.existsById(id)) {
            tutorRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Transactional
    public TutorResponse update(TutorUpdateRequest request) {
        try {
            var tutor = tutorRepository.getReferenceById(request.id());
            updateData(tutor, request);
            return new TutorResponse(tutor);

        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException();
        }
    }

    private void updateData(Tutor entity, TutorUpdateRequest obj) {
        if (obj.nome() != null) {
            entity.setNome(obj.nome());
        }
        if (obj.email() != null) {
            entity.setEmail(obj.email());
        }
    }
}