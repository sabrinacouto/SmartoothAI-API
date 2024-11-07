package com.example.SmartoothAI.services;

import com.example.SmartoothAI.dto.ProcedimentoDTO;
import com.example.SmartoothAI.model.Procedimento;
import com.example.SmartoothAI.repository.ProcedimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProcedimentoService {

    @Autowired
    private ProcedimentoRepository procedimentoRepository;

    public ResponseEntity<List<ProcedimentoDTO>> getAllProcedimentos() {
        List<Procedimento> procedimentos = procedimentoRepository.findAll();
        List<ProcedimentoDTO> procedimentosDTO = procedimentos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(procedimentosDTO);
    }

    public ResponseEntity<ProcedimentoDTO> getProcedimentoById(Long id) {
        Optional<Procedimento> procedimento = procedimentoRepository.findById(id);
        if (procedimento.isPresent()) {
            return ResponseEntity.ok(toDTO(procedimento.get()));
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<String> save(ProcedimentoDTO procedimentoDTO) {
        Procedimento procedimento = toEntity(procedimentoDTO);
        procedimentoRepository.save(procedimento);
        return ResponseEntity.ok("Procedimento criado com sucesso.");
    }

    public ResponseEntity<String> update(Long id, ProcedimentoDTO procedimentoDTO) {
        if (!procedimentoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Procedimento procedimento = toEntity(procedimentoDTO);
        procedimento.setProcedimentoId(id);
        procedimentoRepository.save(procedimento);
        return ResponseEntity.ok("Procedimento atualizado com sucesso.");
    }

    public ResponseEntity<String> delete(Long id) {
        if (!procedimentoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        procedimentoRepository.deleteById(id);
        return ResponseEntity.ok("Procedimento deletado com sucesso.");
    }

    private ProcedimentoDTO toDTO(Procedimento procedimento) {
        ProcedimentoDTO procedimentoDTO = new ProcedimentoDTO();
        procedimentoDTO.setProcedimentoId(procedimento.getProcedimentoId());
        procedimentoDTO.setNomeProcedimento(procedimento.getNomeProcedimento());
        procedimentoDTO.setDescricao(procedimento.getDescricao());
        procedimentoDTO.setInclusaoPlano(procedimento.getInclusaoPlano());
        procedimentoDTO.setProntuarioId(procedimento.getProntuarioId());
        procedimentoDTO.setSistemaPontosId(procedimento.getSistemaPontosId());
        procedimentoDTO.setUsuarioPacienteId(procedimento.getUsPacienteId());
        return procedimentoDTO;
    }

    private Procedimento toEntity(ProcedimentoDTO procedimentoDTO) {
        Procedimento procedimento = new Procedimento();
        procedimento.setNomeProcedimento(procedimentoDTO.getNomeProcedimento());
        procedimento.setDescricao(procedimentoDTO.getDescricao());
        procedimento.setInclusaoPlano(procedimentoDTO.getInclusaoPlano());
        procedimento.setProntuarioId(procedimentoDTO.getProntuarioId());
        procedimento.setSistemaPontosId(procedimentoDTO.getSistemaPontosId());
        procedimento.setUsPacienteId(procedimentoDTO.getUsuarioPacienteId());
        return procedimento;
    }
}

