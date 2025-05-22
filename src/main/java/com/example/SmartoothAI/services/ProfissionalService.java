package com.example.SmartoothAI.services;


import com.example.SmartoothAI.dto.ProfissionalDTO;
import com.example.SmartoothAI.dto.UsuarioPacienteDTO;
import com.example.SmartoothAI.exceptions.EmailAlreadyExistsException;
import com.example.SmartoothAI.model.Profissional;
import com.example.SmartoothAI.producer.ProfissionalProducer;
import com.example.SmartoothAI.repository.ProfissionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfissionalService {

    private final ProfissionalRepository profissionalRepository;

    private final ProfissionalProducer profissionalProducer;

    public ProfissionalDTO authenticateUser(String email, String senha) {
        Optional<Profissional> profissionalOpt = profissionalRepository.findByEmail(email);
        if (profissionalOpt.isPresent()) {
            Profissional profissional = profissionalOpt.get();
            if (profissional.getSenha().equals(senha)) {
                return toDTO(profissional);
            }
        }
        return null;
    }


    public ProfissionalDTO createProfissional(ProfissionalDTO profissionalDTO) {
        if (profissionalRepository.existsByEmail(profissionalDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Email já cadastrado.");
        }

        Profissional profissional = toEntity(profissionalDTO);
        profissional = profissionalRepository.save(profissional);
        ProfissionalDTO dto = toDTO(profissional);
        profissionalProducer.enviarProfissionalCriado(dto);
        return toDTO(profissional);
    }

    public ProfissionalDTO getProfissionalById(Long id) {
        Optional<Profissional> profissionalOpt = profissionalRepository.findById(id);
        if (profissionalOpt.isEmpty()) {
            return null;
        }
        return toDTO(profissionalOpt.get());
    }

    public ProfissionalDTO updateProfissional(Long id, ProfissionalDTO profissionalDTO) {
        Profissional profissional = profissionalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Profissional não encontrado."));

        if (!profissional.getEmail().equals(profissionalDTO.getEmail())
                && profissionalRepository.existsByEmail(profissionalDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Email já cadastrado.");
        }

        profissional.setNome(profissionalDTO.getNome());
        profissional.setEmail(profissionalDTO.getEmail());
        profissional.setContato(profissionalDTO.getContato());


        profissional = profissionalRepository.save(profissional);
        return toDTO(profissional);
    }

    public void deleteProfissional(Long id) {
        if (!profissionalRepository.existsById(id)) {
            throw new IllegalArgumentException("Profissional não encontrado.");
        }
        profissionalRepository.deleteById(id);
    }


    private Profissional toEntity(ProfissionalDTO dto) {
        Profissional profissional = new Profissional();
        profissional.setNome(dto.getNome());
        profissional.setEmail(dto.getEmail());
        profissional.setContato(dto.getContato());
        profissional.setEspecialidade(dto.getEspecialidade());
        profissional.setExperiencia(dto.getExperiencia());
        profissional.setSenha(dto.getSenha());

        return profissional;
    }

    private ProfissionalDTO toDTO(Profissional entity) {
        ProfissionalDTO dto = new ProfissionalDTO();
        dto.setProfissionalId(entity.getProfissionalId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setContato(entity.getContato());
        dto.setEspecialidade(entity.getEspecialidade());
        dto.setExperiencia(entity.getExperiencia());
        dto.setSenha(entity.getSenha());

        return dto;
    }
}

