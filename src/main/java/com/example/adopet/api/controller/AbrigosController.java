package com.example.adopet.api.controller;

import com.example.adopet.api.dto.Abrigo.DadosAtualizacaoAbrigo;
import com.example.adopet.api.dto.Abrigo.DadosCadastroAbrigo;
import com.example.adopet.api.dto.Abrigo.DadosDetalhesAbrigo;
import com.example.adopet.api.dto.Abrigo.DadosAbrigo;
import com.example.adopet.api.services.AbrigoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/abrigos")
@Tag(name = "Abrigo")
public class AbrigosController {

    private final AbrigoService abrigoService;

    public AbrigosController(AbrigoService abrigoService) {
        this.abrigoService = abrigoService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Endpoint para criar um novo abrigo")
    public ResponseEntity<DadosDetalhesAbrigo> save(@RequestBody @Valid DadosCadastroAbrigo dados, UriComponentsBuilder uriBuilder) {
        var abrigo = abrigoService.save(dados);
        var uri = uriBuilder.path("/tutor/{id}").buildAndExpand(abrigo.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhesAbrigo(abrigo));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Endpoint para listar todos os abrigos")
    public ResponseEntity<List<DadosDetalhesAbrigo>> findAll() {
        List<DadosDetalhesAbrigo> abrigos = abrigoService.findAll();

        return ResponseEntity.ok(abrigos);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Endpoint para listar abrigo por id")
    public ResponseEntity findById(@PathVariable Long id) {
        return ResponseEntity.ok(abrigoService.findById(id));
    }


    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Endpoint para remover abrigo por id")
    public ResponseEntity delete(@PathVariable Long id) {

        abrigoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Endpoint para atualizar um abrigo")
    public ResponseEntity update(@RequestBody @Valid DadosAtualizacaoAbrigo dados) {
        var tutor = abrigoService.update(dados);
        return ResponseEntity.ok().body(tutor);
    }
}
