package com.example.SmartoothAI.controller;

import com.example.SmartoothAI.dto.RecomendacaoTratDTO;
import com.example.SmartoothAI.model.RecomendacaoTrat;
import com.example.SmartoothAI.services.RecomendacaoTratService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recomendacoes")
public class RecomendacaoTratController {

    private final RecomendacaoTratService recomendacaoTratService;

    public RecomendacaoTratController(RecomendacaoTratService recomendacaoTratService) {
        this.recomendacaoTratService = recomendacaoTratService;
    }

    @GetMapping
    public ResponseEntity<List<RecomendacaoTrat>> getAllRecomendacoes() {
        List<RecomendacaoTrat> recomendacoes = recomendacaoTratService.getAllRecomendacoes();
        return ResponseEntity.ok(recomendacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecomendacaoTrat> getRecomendacaoById(@PathVariable Long id) {
        RecomendacaoTrat recomendacao = recomendacaoTratService.getRecomendacaoById(id);
        return ResponseEntity.ok(recomendacao);
    }


    @PostMapping
    public ResponseEntity<String> createRecomendacao(@Valid @RequestBody RecomendacaoTratDTO recomendacaoTratDTO) {
        return recomendacaoTratService.save(recomendacaoTratDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRecomendacao(@PathVariable Long id, @Valid @RequestBody RecomendacaoTratDTO recomendacaoTratDTO) {
        return recomendacaoTratService.update(id, recomendacaoTratDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecomendacao(@PathVariable Long id) {
        return recomendacaoTratService.delete(id);
    }
}

