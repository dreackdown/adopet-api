package com.example.adopet.api.controller;

import com.example.adopet.api.dto.Abrigo.DadosAtualizacaoAbrigo;
import com.example.adopet.api.dto.Abrigo.DadosCadastroAbrigo;
import com.example.adopet.api.dto.Abrigo.DadosDetalhamentoAbrigo;
import com.example.adopet.api.dto.Abrigo.DadosListagemAbrigo;
import com.example.adopet.api.dto.Tutor.DadosAtualizacaoTutor;
import com.example.adopet.api.dto.Tutor.DadosCadastroTutor;
import com.example.adopet.api.dto.Tutor.DadosDetalhamentoTutor;
import com.example.adopet.api.dto.Tutor.DadosListagemTutor;
import com.example.adopet.api.services.AbrigoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/abrigos")
@Tag(name = "Abrigo")
public class AbrigoController {

    private AbrigoService abrigoService;

    public AbrigoController(AbrigoService abrigoService) {
        this.abrigoService = abrigoService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Endpoint para listar todos os abrigos")
    public ResponseEntity<List<DadosListagemAbrigo>> findAll() {
        List<DadosListagemAbrigo> abrigos = abrigoService.findAll();

        return ResponseEntity.ok(abrigos);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Endpoint para listar abrigo por id")
    public ResponseEntity findById(@PathVariable Long id) {
        return ResponseEntity.ok(abrigoService.findById(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Endpoint para criar um novo abrigo")
    public ResponseEntity save(@RequestBody @Valid DadosCadastroAbrigo dados, UriComponentsBuilder uriBuilder) {
        var tutor = abrigoService.save(dados);
        var uri = uriBuilder.path("/tutor/{id}").buildAndExpand(tutor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoAbrigo(tutor));
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
