package br.com.hfl.adopet.api.domain.abrigo;

import br.com.hfl.adopet.api.domain.perfil.EPerfil;
import br.com.hfl.adopet.api.infra.payload.response.AbrigoResponse;
import br.com.hfl.adopet.api.domain.perfil.Perfil;
import br.com.hfl.adopet.api.domain.perfil.PerfilRepository;
import br.com.hfl.adopet.api.infra.payload.request.AbrigoRequest;
import br.com.hfl.adopet.api.infra.payload.request.AbrigoUpdateRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AbrigoService {

    private final AbrigoRepository abrigoRepository;
    private final PerfilRepository perfilRepository;

    public AbrigoService(AbrigoRepository abrigoRepository,
                         PerfilRepository perfilRepository) {
        this.abrigoRepository = abrigoRepository;
        this.perfilRepository = perfilRepository;
    }

    @Transactional
    public Abrigo save(AbrigoRequest request) {
        var abrigo = new Abrigo(request);

        Set<Perfil> roles = new HashSet<>();

        Perfil perfilAbrigo = perfilRepository.findByNome(EPerfil.ROLE_ABRIGO)
                .orElseThrow(() -> new RuntimeException("Erro: Perfil não encontrado."));

        roles.add(perfilAbrigo);
        abrigo.setPerfis(roles);
        return abrigoRepository.save(abrigo);
    }

    public AbrigoResponse findById(Long id) {
        try {
            var abrigo = abrigoRepository.getReferenceById(id);
            return new AbrigoResponse(abrigo);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException();
        }
    }

    public List<AbrigoResponse> findAll() {
        var abrigos = abrigoRepository.findAll();

        if (abrigos.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return abrigos.stream().map(AbrigoResponse::new).collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        if (abrigoRepository.existsById(id)) {
            abrigoRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Transactional
    public AbrigoResponse update(AbrigoUpdateRequest request) {
        try {
            var abrigo = abrigoRepository.getReferenceById(request.id());
            updateData(abrigo, request);
            return new AbrigoResponse(abrigo);

        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException();
        }
    }

    private void updateData(Abrigo entity, AbrigoUpdateRequest obj) {
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