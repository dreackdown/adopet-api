package br.com.hfl.adopet.api.controller;

import br.com.hfl.adopet.api.domain.tutor.TutorService;
import br.com.hfl.adopet.api.infra.payload.request.TutorRequest;
import br.com.hfl.adopet.api.infra.payload.request.TutorUpdateRequest;
import br.com.hfl.adopet.api.infra.payload.response.TutorResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/tutores")
public class TutoresController {

    private final TutorService tutorService;

    public TutoresController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @PostMapping
    @Operation(summary = "Endpoint para criar um novo tutor")
    public ResponseEntity<?> save(@RequestBody @Valid TutorRequest request, UriComponentsBuilder uriBuilder) {
        if (!request.senha().equals(request.confirmacaoSenha())) {
            return ResponseEntity.badRequest().body("Senha e confirmação senha não conferem!");
        }

        var tutor = tutorService.save(request);
        var uri = uriBuilder.path("/api/tutores/{id}").buildAndExpand(tutor.getId()).toUri();
        return ResponseEntity.created(uri).body(new TutorResponse(tutor));
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Endpoint para listar tutor por id")
    public ResponseEntity<TutorResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(tutorService.findById(id));
    }

    @GetMapping()
    @Operation(summary = "Endpoint para listar todos os tutores")
    public ResponseEntity<List<TutorResponse>> findAll() {
        return ResponseEntity.ok(tutorService.findAll());
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Endpoint para remover tutor por id")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        tutorService.deleteById(id);
        return ResponseEntity.ok("tutor apagado com sucesso!");
    }

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH})
    @Operation(summary = "Endpoint para atualizar um tutor")
    public ResponseEntity<?> update(@RequestBody @Valid TutorUpdateRequest request) {
        if (request.senha() != null && (!request.senha().equals(request.confirmacaoSenha()))) {
            return ResponseEntity.badRequest().body("Senha e confirmação senha não conferem!");
        }
        return ResponseEntity.ok(tutorService.update(request));
    }
}