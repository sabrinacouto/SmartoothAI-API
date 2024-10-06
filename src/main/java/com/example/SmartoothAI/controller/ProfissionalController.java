package com.example.SmartoothAI.controller;

import com.example.SmartoothAI.dto.ProfissionalDTO;
import com.example.SmartoothAI.model.Profissional;
import com.example.SmartoothAI.services.ProfissionalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/profissional")
public class ProfissionalController {

    private final ProfissionalService profissionalService;

    public ProfissionalController(ProfissionalService profissionalService) {
        this.profissionalService = profissionalService;
    }

    @GetMapping
    public ResponseEntity<List<Profissional>> getAllProfissionais() {
        List<Profissional> profissionais = profissionalService.getAllProfissionais();
        return ResponseEntity.ok(profissionais);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profissional> getProfissionalById(@PathVariable Long id) {
        Profissional profissional = profissionalService.getProfissionalById(id);
        return ResponseEntity.ok(profissional);
    }

    @PostMapping
    public ResponseEntity<String> createProfissional(@Valid @RequestBody ProfissionalDTO profissionalDTO) {
        return profissionalService.createProfissional(profissionalDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProfissional(@PathVariable Long id,
                                                     @Valid @RequestBody ProfissionalDTO profissionalDTO) {
        return profissionalService.updateProfissional(id, profissionalDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProfissional(@PathVariable Long id) {
        return profissionalService.deleteProfissional(id);
    }
}



