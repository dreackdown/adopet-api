package com.example.adopet.api.controller;

import com.example.adopet.api.domain.tutor.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> save(@RequestBody @Valid TutorRequestDTO request, UriComponentsBuilder uriBuilder) {
        if (!request.senha().equals(request.confirmacaoSenha())) {
            return ResponseEntity.badRequest().body("Senha e confirmação senha não conferem!");
        }

        var tutor = tutorService.save(request);
        var uri = uriBuilder.path("/api/tutores/{id}").buildAndExpand(tutor.getId()).toUri();
        return ResponseEntity.created(uri).body(new TutorResponseDTO(tutor));
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Endpoint para listar tutor por id")
    public ResponseEntity<TutorResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(tutorService.findById(id));
    }

    @GetMapping()
    @Operation(summary = "Endpoint para listar todos os tutores")
    public ResponseEntity<List<TutorResponseDTO>> findAll() {
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
    public ResponseEntity<?> update(@RequestBody @Valid TutorUpdateDTO request) {
        if (request.senha() != null && (!request.senha().equals(request.confirmacaoSenha()))) {
            return ResponseEntity.badRequest().body("Senha e confirmação senha não conferem!");
        }
        return ResponseEntity.ok(tutorService.update(request));
    }
}