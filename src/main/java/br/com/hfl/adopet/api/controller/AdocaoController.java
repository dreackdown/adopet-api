package br.com.hfl.adopet.api.controller;

import br.com.hfl.adopet.api.infra.payload.request.AdocaoRequest;
import br.com.hfl.adopet.api.infra.payload.response.AdocaoResponse;
import br.com.hfl.adopet.api.domain.adocao.AdocaoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ABRIGO')")
    @Operation(summary = "Endpoint para cancelar uma adocao por id")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        adocaoService.deleteById(id);
        return ResponseEntity.ok("adocao removida");
    }
}