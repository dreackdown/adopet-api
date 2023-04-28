package com.example.adopet.api.controller;

import com.example.adopet.api.dto.Tutor.*;
import com.example.adopet.api.entities.Tutor;
import io.swagger.v3.oas.annotations.Operation;
import com.example.adopet.api.services.TutorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tutores")
@Tag(name = "Tutor")
public class TutoresController {

    private final TutorService tutorService;

    public TutoresController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Endpoint para criar um novo tutor")
    public ResponseEntity<Object> save(@RequestBody @Valid DadosCadastroTutor dados, UriComponentsBuilder uriBuilder) {
        if (!dados.senha().equals(dados.confirmacaoSenha())) {
            return ResponseEntity.badRequest().body("Senha e confirmação senha não conferem!");
        }

        var tutor = tutorService.save(dados);
        var uri = uriBuilder.path("/tutor/{id}").buildAndExpand(tutor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosTutor(tutor));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Endpoint para listar todos os tutores")
    public ResponseEntity<List<DadosDetalhesTutor>> findAll() {
        List<DadosDetalhesTutor> tutores = tutorService.findAll();

        return ResponseEntity.ok(tutores);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Endpoint para listar tutor por id")
    public ResponseEntity<DadosDetalhesTutor> findById(@PathVariable Long id) {
        return ResponseEntity.ok(tutorService.findById(id));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Endpoint para remover tutor por id")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        tutorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Endpoint para atualizar um tutor")
    public ResponseEntity<DadosTutor> update(@RequestBody @Valid DadosAtualizacaoTutor dados) {
        var tutor = tutorService.update(dados);
        return ResponseEntity.ok().body(tutor);
    }
}
