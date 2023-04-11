package com.example.adopet.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import com.example.adopet.api.dto.Tutor.DadosAtualizacaoTutor;
import com.example.adopet.api.dto.Tutor.DadosCadastroTutor;
import com.example.adopet.api.dto.Tutor.DadosDetalhamentoTutor;
import com.example.adopet.api.dto.Tutor.DadosListagemTutor;
import com.example.adopet.api.services.TutorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/tutores")
@Tag(name = "Tutor")
public class TutorController {

    private final TutorService tutorService;

    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Endpoint para listar todos os tutores")
    public ResponseEntity<List<DadosListagemTutor>> findAll() {
        List<DadosListagemTutor> tutores = tutorService.findAll();

        return ResponseEntity.ok(tutores);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Endpoint para listar tutor por id")
    public ResponseEntity findById(@PathVariable Long id) {
        return ResponseEntity.ok(tutorService.findById(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Endpoint para criar um novo tutor")
    public ResponseEntity save(@RequestBody @Valid DadosCadastroTutor dados, UriComponentsBuilder uriBuilder) {
        var tutor = tutorService.save(dados);
        var uri = uriBuilder.path("/tutor/{id}").buildAndExpand(tutor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoTutor(tutor));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Endpoint para remover tutor por id")
    public ResponseEntity delete(@PathVariable Long id) {

        tutorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Endpoint para atualizar um tutor")
    public ResponseEntity update(@RequestBody @Valid DadosAtualizacaoTutor dados) {
        var tutor = tutorService.update(dados);
        return ResponseEntity.ok().body(tutor);
    }
}
