package com.example.SmartoothAI.services;

import com.example.SmartoothAI.dto.ProfissionalDTO;
import com.example.SmartoothAI.exceptions.ResourceNotFoundException;
import com.example.SmartoothAI.model.Profissional;
import com.example.SmartoothAI.repository.ProfissionalRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfissionalService {

    private final ProfissionalRepository profissionalRepository;

    public List<Profissional> getAllProfissionais() {
        return profissionalRepository.findAll();
    }

    public Profissional getProfissionalById(Long id) {
        return profissionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado com o ID: " + id));
    }

    @Transactional
    public ResponseEntity<String> createProfissional(ProfissionalDTO profissionalDTO) {
        Profissional profissional = new Profissional();
        profissional.setNome(profissionalDTO.getNome());
        profissional.setEspecialidade(profissionalDTO.getEspecialidade());
        profissional.setExperiencia(profissionalDTO.getExperiencia());
        profissional.setContato(profissionalDTO.getContato());
        profissional.setDataRegistro(profissionalDTO.getDataRegistro());

        profissionalRepository.save(profissional);
        return ResponseEntity.status(201).body("Profissional cadastrado no sistema com sucesso.");
    }

    @Transactional
    public ResponseEntity<String> updateProfissional(Long id, ProfissionalDTO profissionalDTO) {
        Profissional profissional = profissionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado com o ID: " + id));

        profissional.setNome(profissionalDTO.getNome());
        profissional.setEspecialidade(profissionalDTO.getEspecialidade());
        profissional.setExperiencia(profissionalDTO.getExperiencia());
        profissional.setContato(profissionalDTO.getContato());
        profissional.setDataRegistro(profissionalDTO.getDataRegistro());

        profissionalRepository.save(profissional);
        return ResponseEntity.ok("Profissional atualizado com sucesso.");
    }

    @Transactional
    public ResponseEntity<String> deleteProfissional(Long id) {
        profissionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado com o ID: " + id));

        profissionalRepository.deleteById(id);
        return ResponseEntity.ok("Profissional deletado com sucesso.");
    }
}

