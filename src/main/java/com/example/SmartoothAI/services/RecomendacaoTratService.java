package com.example.SmartoothAI.services;

import com.example.SmartoothAI.dto.RecomendacaoTratDTO;
import com.example.SmartoothAI.exceptions.ResourceNotFoundException;
import com.example.SmartoothAI.model.RecomendacaoTrat;
import com.example.SmartoothAI.repository.RecomendacaoTratRepository;
import com.example.SmartoothAI.model.Plano;
import com.example.SmartoothAI.repository.PlanoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecomendacaoTratService {

    private final RecomendacaoTratRepository recomendacaoTratRepository;
    private final PlanoRepository planoRepository;

    public List<RecomendacaoTrat> getAllRecomendacoes() {
        return recomendacaoTratRepository.findAll();
    }

    public RecomendacaoTrat getRecomendacaoById(Long id) {
        return recomendacaoTratRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recomendação não encontrada com o ID: " + id));
    }

    @Transactional
    public ResponseEntity<String> save(RecomendacaoTratDTO recomendacaoTratDTO) {
        Plano plano = planoRepository.findById(recomendacaoTratDTO.getPlanoId())
                .orElseThrow(() -> new ResourceNotFoundException("Plano não encontrado com o ID: " + recomendacaoTratDTO.getPlanoId()));

        RecomendacaoTrat recomendacaoTrat = new RecomendacaoTrat();
        recomendacaoTrat.setDataRec(recomendacaoTratDTO.getDataRec());
        recomendacaoTrat.setPlano(plano);

        recomendacaoTratRepository.save(recomendacaoTrat);
        return ResponseEntity.status(201).body("Recomendação criada com sucesso.");
    }

    @Transactional
    public ResponseEntity<String> update(Long id, RecomendacaoTratDTO recomendacaoTratDTO) {
        RecomendacaoTrat recomendacaoTrat = recomendacaoTratRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recomendação não encontrada com o ID: " + id));

        Plano plano = planoRepository.findById(recomendacaoTratDTO.getPlanoId())
                .orElseThrow(() -> new ResourceNotFoundException("Plano não encontrado com o ID: " + recomendacaoTratDTO.getPlanoId()));

        recomendacaoTrat.setDataRec(recomendacaoTratDTO.getDataRec());
        recomendacaoTrat.setPlano(plano);

        recomendacaoTratRepository.save(recomendacaoTrat);
        return ResponseEntity.ok("Recomendação atualizada com sucesso.");
    }

    @Transactional
    public ResponseEntity<String> delete(Long id) {
        recomendacaoTratRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recomendação não encontrada com o ID: " + id));

        recomendacaoTratRepository.deleteById(id);
        return ResponseEntity.ok("Recomendação deletada com sucesso.");
    }
}


