package com.example.adopet.api.controller;

import com.example.adopet.api.dto.Abrigo.DadosAbrigo;
import com.example.adopet.api.dto.Abrigo.DadosAtualizacaoAbrigo;
import com.example.adopet.api.dto.Abrigo.DadosCadastroAbrigo;
import com.example.adopet.api.dto.Abrigo.DadosDetalhesAbrigo;
import com.example.adopet.api.dto.Pet.DadosAtualizacaoPet;
import com.example.adopet.api.dto.Pet.DadosCadastroPet;
import com.example.adopet.api.dto.Pet.DadosDetalhesPet;
import com.example.adopet.api.dto.Pet.DadosPet;
import com.example.adopet.api.dto.Tutor.DadosAtualizacaoTutor;
import com.example.adopet.api.dto.Tutor.DadosDetalhesTutor;
import com.example.adopet.api.services.AbrigoService;
import com.example.adopet.api.services.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/pets")
@Tag(name = "Pet")
public class PetsController {

    private final PetService petService;

    public PetsController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Endpoint para criar um novo pet")
    public ResponseEntity<DadosPet> save(@RequestBody @Valid DadosCadastroPet dados, UriComponentsBuilder uriBuilder) {
        var pet = petService.save(dados);
        var uri = uriBuilder.path("/pet/{id}").buildAndExpand(pet.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosPet(pet));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Endpoint para listar todos os pets")
    public ResponseEntity<List<DadosDetalhesPet>> findAll() {
        List<DadosDetalhesPet> pets = petService.findAll();

        return ResponseEntity.ok(pets);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Endpoint para listar pet por id")
    public ResponseEntity<DadosDetalhesPet> findById(@PathVariable Long id) {
        return ResponseEntity.ok(petService.findById(id));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Endpoint para remover pet por id")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        petService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Endpoint para atualizar um pet")
    public ResponseEntity<DadosPet> update(@RequestBody @Valid DadosAtualizacaoPet dados) {
        var pet = petService.update(dados);
        return ResponseEntity.ok().body(pet);
    }
}
