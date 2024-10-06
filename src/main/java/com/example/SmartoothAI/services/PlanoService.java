package com.example.SmartoothAI.services;

import com.example.SmartoothAI.dto.PlanoDTO;
import com.example.SmartoothAI.exceptions.ResourceNotFoundException;
import com.example.SmartoothAI.model.Plano;
import com.example.SmartoothAI.repository.PlanoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanoService {

    private final PlanoRepository planoRepository;

    public List<Plano> getAllPlanos() {
        return planoRepository.findAll();
    }

    public Plano getPlanoById(Long id) {
        return planoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plano não encontrado com o ID: " + id));
    }

    @Transactional
    public ResponseEntity<String> createPlano(PlanoDTO planoDTO) {
        Plano plano = new Plano();
        plano.setDescricao(planoDTO.getDescricao());
        plano.setDataInicio(planoDTO.getDataInicio());
        plano.setDataFim(planoDTO.getDataFim());
        plano.setValor(planoDTO.getValor());

        planoRepository.save(plano);
        return ResponseEntity.status(201).body("Plano criado com sucesso.");
    }

    @Transactional
    public ResponseEntity<String> updatePlano(Long id, PlanoDTO planoDTO) {
        Plano plano = planoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plano não encontrado com o ID: " + id));

        plano.setDescricao(planoDTO.getDescricao());
        plano.setDataInicio(planoDTO.getDataInicio());
        plano.setDataFim(planoDTO.getDataFim());
        plano.setValor(planoDTO.getValor());

        planoRepository.save(plano);
        return ResponseEntity.ok("Plano atualizado com sucesso.");
    }

    @Transactional
    public ResponseEntity<String> deletePlano(Long id) {
        planoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plano não encontrado com o ID: " + id));

        planoRepository.deleteById(id);
        return ResponseEntity.ok("Plano deletado com sucesso.");
    }
}

