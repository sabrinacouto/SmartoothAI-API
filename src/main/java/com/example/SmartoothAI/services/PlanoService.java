package com.example.SmartoothAI.services;

import com.example.SmartoothAI.dto.PlanoDTO;
import com.example.SmartoothAI.model.Plano;
import com.example.SmartoothAI.repository.PlanoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


import com.example.SmartoothAI.model.UsuarioPaciente;
import com.example.SmartoothAI.repository.UsuarioPacienteRepository;

@Service
@RequiredArgsConstructor
public class PlanoService {

    private final PlanoRepository planoRepository;
    private final UsuarioPacienteRepository usuarioPacienteRepository;


    @Transactional
    public void createPlano(PlanoDTO planoDTO) {
        Optional<UsuarioPaciente> usuarioPaciente = usuarioPacienteRepository.findById(planoDTO.getUsuarioPacienteId());

        if (usuarioPaciente.isPresent()) {
            Plano plano = new Plano();
            plano.setDescricao(planoDTO.getDescricao());
            plano.setTipoPlano(planoDTO.getTipoPlano());
            plano.setMarcaPlano(planoDTO.getMarcaPlano());
            plano.setTipoPagamento(planoDTO.getTipoPagamento());
            plano.setUsuarioPaciente(usuarioPaciente.get());

            planoRepository.save(plano);
        } else {
            throw new IllegalArgumentException("Usuário paciente não encontrado.");
        }
    }

    public PlanoDTO getPlanoById(Long id) {
        Optional<Plano> plano = planoRepository.findById(id);
        if (plano.isPresent()) {
            PlanoDTO planoDTO = new PlanoDTO();
            planoDTO.setPlanoId(plano.get().getPlanoId());
            planoDTO.setDescricao(plano.get().getDescricao());
            planoDTO.setTipoPlano(plano.get().getTipoPlano());
            planoDTO.setMarcaPlano(plano.get().getMarcaPlano());
            planoDTO.setTipoPagamento(plano.get().getTipoPagamento());
            planoDTO.setUsuarioPacienteId(plano.get().getUsuarioPaciente().getPacienteId());
            return planoDTO;
        }
        return null;
    }

    @Transactional
    public void updatePlano(Long id, PlanoDTO planoDTO) {
        Optional<Plano> plano = planoRepository.findById(id);
        if (plano.isPresent()) {
            Optional<UsuarioPaciente> usuarioPaciente = usuarioPacienteRepository.findById(planoDTO.getUsuarioPacienteId());
            if (usuarioPaciente.isPresent()) {
                Plano planoAtualizado = plano.get();
                planoAtualizado.setDescricao(planoDTO.getDescricao());
                planoAtualizado.setTipoPlano(planoDTO.getTipoPlano());
                planoAtualizado.setMarcaPlano(planoDTO.getMarcaPlano());
                planoAtualizado.setTipoPagamento(planoDTO.getTipoPagamento());
                planoAtualizado.setUsuarioPaciente(usuarioPaciente.get());
                planoRepository.save(planoAtualizado);
            } else {
                throw new IllegalArgumentException("Usuário paciente não encontrado.");
            }
        }
    }



    @Transactional
    public void deletePlano(Long id) {
        planoRepository.deleteById(id);
    }


    public List<PlanoDTO> getAllPlanos() {
        List<Plano> planos = planoRepository.findAll();
        return planos.stream().map(plano -> {
            PlanoDTO planoDTO = new PlanoDTO();
            planoDTO.setPlanoId(plano.getPlanoId());
            planoDTO.setDescricao(plano.getDescricao());
            planoDTO.setTipoPlano(plano.getTipoPlano());
            planoDTO.setMarcaPlano(plano.getMarcaPlano());
            planoDTO.setTipoPagamento(plano.getTipoPagamento());
            planoDTO.setUsuarioPacienteId(plano.getUsuarioPaciente().getPacienteId());
            return planoDTO;
        }).toList();
    }


    public List<PlanoDTO> getPlanosByUsuarioId(Long usuarioId) {

        List<Plano> planos = planoRepository. findByUsuarioPaciente_PacienteId(usuarioId);

        return planos.stream().map(plano -> {
            PlanoDTO planoDTO = new PlanoDTO();
            planoDTO.setPlanoId(plano.getPlanoId());
            planoDTO.setTipoPlano(plano.getTipoPlano());
            planoDTO.setDescricao(plano.getDescricao());
            planoDTO.setMarcaPlano(plano.getMarcaPlano());
            planoDTO.setTipoPagamento(plano.getTipoPagamento());
            planoDTO.setUsuarioPacienteId(plano.getUsuarioPaciente().getPacienteId());
            return planoDTO;
        }).toList();
    }
}

