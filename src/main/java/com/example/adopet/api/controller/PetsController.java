package com.example.adopet.api.controller;

import com.example.adopet.api.domain.pet.PetRequestDTO;
import com.example.adopet.api.domain.pet.PetResponseDTO;
import com.example.adopet.api.domain.pet.PetService;
import com.example.adopet.api.domain.pet.PetUpdateDTO;
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
    public ResponseEntity<?> save(@RequestBody @Valid PetRequestDTO request, UriComponentsBuilder uriBuilder) {

        var pet = petService.save(request);
        var uri = uriBuilder.path("/api/pets/{id}").buildAndExpand(pet.getId()).toUri();
        return ResponseEntity.created(uri).body(new PetResponseDTO(pet));
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Endpoint para listar pet por id")
    public ResponseEntity<PetResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(petService.findById(id));
    }

    @GetMapping()
    @Operation(summary = "Endpoint para listar todos os pets")
    public ResponseEntity<List<PetResponseDTO>> findAll() {
        return ResponseEntity.ok(petService.findAll());
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Endpoint para remover pet por id")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        petService.deleteById(id);
        return ResponseEntity.ok("pet apagado com sucesso!");
    }

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<?> update(@RequestBody @Valid PetUpdateDTO request) {
        return ResponseEntity.ok(petService.update(request));
    }
}