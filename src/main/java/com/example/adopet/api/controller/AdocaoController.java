package com.example.adopet.api.controller;

import com.example.adopet.api.infra.payload.request.AdocaoRequest;
import com.example.adopet.api.infra.payload.response.AdocaoResponse;
import com.example.adopet.api.domain.adocao.AdocaoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/adocao")
public class AdocaoController {

    private final AdocaoService adocaoService;

    public AdocaoController(AdocaoService adocaoService) {
        this.adocaoService = adocaoService;
    }

    @PostMapping
    @Operation(summary = "Endpoint para fazer uma adocao")
    public ResponseEntity<?> save(@RequestBody @Valid AdocaoRequest request) {
        var adocao = adocaoService.save(request);
        return ResponseEntity.ok(new AdocaoResponse(adocao));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Endpoint para remover uma adocao por id")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        adocaoService.deleteById(id);
        return ResponseEntity.ok("adocao removida");
    }
}