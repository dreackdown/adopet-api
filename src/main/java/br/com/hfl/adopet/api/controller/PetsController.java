package br.com.hfl.adopet.api.controller;

import br.com.hfl.adopet.api.infra.payload.request.PetRequest;
import br.com.hfl.adopet.api.infra.payload.request.PetUpdateRequest;
import br.com.hfl.adopet.api.infra.payload.response.PetResponse;
import br.com.hfl.adopet.api.domain.pet.PetService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetsController {

    private final PetService petService;

    public PetsController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    @Operation(summary = "Endpoint para criar um novo pet")
    public ResponseEntity<?> save(@RequestBody @Valid PetRequest request, UriComponentsBuilder uriBuilder) {

        var pet = petService.save(request);
        var uri = uriBuilder.path("/api/pets/{id}").buildAndExpand(pet.getId()).toUri();
        return ResponseEntity.created(uri).body(new PetResponse(pet));
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Endpoint para listar pet por id")
    public ResponseEntity<PetResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(petService.findById(id));
    }

    @GetMapping()
    @Operation(summary = "Endpoint para listar todos os pets")
    public ResponseEntity<List<PetResponse>> findAll() {
        return ResponseEntity.ok(petService.findAll());
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Endpoint para remover pet por id")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        petService.deleteById(id);
        return ResponseEntity.ok("pet apagado com sucesso!");
    }

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<?> update(@RequestBody @Valid PetUpdateRequest request) {
        return ResponseEntity.ok(petService.update(request));
    }
}