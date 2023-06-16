package com.example.adopet.api.controller;

import com.example.adopet.api.domain.abrigo.AbrigoRequestDTO;
import com.example.adopet.api.domain.abrigo.AbrigoResponseDTO;
import com.example.adopet.api.domain.abrigo.AbrigoService;
import com.example.adopet.api.domain.abrigo.AbrigoUpdateDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/abrigos")
public class AbrigosController {

    private final AbrigoService abrigoService;

    public AbrigosController(AbrigoService abrigoService) {
        this.abrigoService = abrigoService;
    }

    @PostMapping
    @Operation(summary = "Endpoint para criar um novo abrigo")
    public ResponseEntity<?> save(@RequestBody @Valid AbrigoRequestDTO request, UriComponentsBuilder uriBuilder) {

        var abrigo = abrigoService.save(request);
        var uri = uriBuilder.path("/api/abrigos/{id}").buildAndExpand(abrigo.getId()).toUri();
        return ResponseEntity.created(uri).body(new AbrigoResponseDTO(abrigo));
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Endpoint para listar abrigo por id")
    public ResponseEntity<AbrigoResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(abrigoService.findById(id));
    }

    @GetMapping()
    @Operation(summary = "Endpoint para listar todos os abrigos")
    public ResponseEntity<List<AbrigoResponseDTO>> findAll() {
        return ResponseEntity.ok(abrigoService.findAll());
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Endpoint para remover abrigo por id")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        abrigoService.deleteById(id);
        return ResponseEntity.ok("abrigo apagado com sucesso!");
    }

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<?> update(@RequestBody @Valid AbrigoUpdateDTO request) {
        return ResponseEntity.ok(abrigoService.update(request));
    }
}