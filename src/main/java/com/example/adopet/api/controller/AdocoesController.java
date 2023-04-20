package com.example.adopet.api.controller;

import com.example.adopet.api.dto.Adocao.DadosAdocao;
import com.example.adopet.api.dto.Adocao.DadosDetalhesAdocao;
import com.example.adopet.api.services.AdocaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/adocao")
@Tag(name = "Adoção")
public class AdocoesController {

    private final AdocaoService adocaoService;

    public AdocoesController(AdocaoService adocaoService) {
        this.adocaoService = adocaoService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Endpoint para criar uma nova adocao")
    public ResponseEntity<DadosDetalhesAdocao> adotar(@RequestBody @Valid DadosAdocao dados) {
        var dto = adocaoService.adotar(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Endpoint para remover abrigo por id")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        adocaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
