package com.example.SmartoothAI.controller;

import com.example.SmartoothAI.dto.PlanoDTO;
import com.example.SmartoothAI.model.Plano;
import com.example.SmartoothAI.services.PlanoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planos")
public class PlanoController {

    private final PlanoService planoService;

    public PlanoController(PlanoService planoService) {
        this.planoService = planoService;
    }

    @GetMapping
    public ResponseEntity<List<Plano>> getAllPlanos() {
        List<Plano> planos = planoService.getAllPlanos();
        return ResponseEntity.ok(planos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plano> getPlanoById(@PathVariable Long id) {
        Plano plano = planoService.getPlanoById(id);
        return ResponseEntity.ok(plano);
    }

    @PostMapping
    public ResponseEntity<String> createPlano(@Valid @RequestBody PlanoDTO planoDTO) {
        return planoService.createPlano(planoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePlano(@PathVariable Long id,
                                              @Valid @RequestBody PlanoDTO planoDTO) {
        return planoService.updatePlano(id, planoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlano(@PathVariable Long id) {
        return planoService.deletePlano(id);
    }
}

