package com.example.SmartoothAI.services;

import com.example.SmartoothAI.dto.UsuarioPacienteDTO;
import com.example.SmartoothAI.exceptions.EmailAlreadyExistsException;
import com.example.SmartoothAI.exceptions.ResourceNotFoundException;
import com.example.SmartoothAI.model.UsuarioPaciente;
import com.example.SmartoothAI.repository.UsuarioPacienteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioPacienteService {

    private final UsuarioPacienteRepository usuarioPacienteRepository;


    public List<UsuarioPaciente> getAllUsuarios() {
        return usuarioPacienteRepository.findAll();
    }

    public UsuarioPaciente getUsuarioById(Long id) {
        return usuarioPacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o ID: " + id));
    }


    @Transactional
    public UsuarioPaciente createUsuario(UsuarioPacienteDTO usuarioPacienteDTO) {
        if (usuarioPacienteRepository.findByEmail(usuarioPacienteDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Já existe um usuário com este e-mail.");
        }

        UsuarioPaciente usuarioPaciente = new UsuarioPaciente();
        usuarioPaciente.setNome(usuarioPacienteDTO.getNome());
        usuarioPaciente.setSobrenome(usuarioPacienteDTO.getSobrenome());
        usuarioPaciente.setEmail(usuarioPacienteDTO.getEmail());
        usuarioPaciente.setDataNasc(usuarioPacienteDTO.getDataNasc());
        usuarioPaciente.setGenero(usuarioPacienteDTO.getGenero());
        usuarioPaciente.setCep(usuarioPacienteDTO.getCep());
        usuarioPaciente.setLogradouro(usuarioPacienteDTO.getLogradouro());
        usuarioPaciente.setNumero(usuarioPacienteDTO.getNumero());
        usuarioPaciente.setComplemento(usuarioPacienteDTO.getComplemento());
        usuarioPaciente.setBairro(usuarioPacienteDTO.getBairro());
        usuarioPaciente.setCidade(usuarioPacienteDTO.getCidade());
        usuarioPaciente.setUf(usuarioPacienteDTO.getUf());
        usuarioPaciente.setContato(usuarioPacienteDTO.getContato());
        usuarioPaciente.setPontos(usuarioPacienteDTO.getPontos());
        usuarioPaciente.setDescontos(usuarioPacienteDTO.getDescontos());

        return usuarioPacienteRepository.save(usuarioPaciente);
    }

    @Transactional
    public UsuarioPaciente updateUsuario(Long id, UsuarioPacienteDTO usuarioPacienteDTO) {
        UsuarioPaciente usuarioPaciente = usuarioPacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o ID: " + id));

        usuarioPaciente.setNome(usuarioPacienteDTO.getNome());
        usuarioPaciente.setSobrenome(usuarioPacienteDTO.getSobrenome());
        usuarioPaciente.setEmail(usuarioPacienteDTO.getEmail());
        usuarioPaciente.setDataNasc(usuarioPacienteDTO.getDataNasc());
        usuarioPaciente.setGenero(usuarioPacienteDTO.getGenero());
        usuarioPaciente.setCep(usuarioPacienteDTO.getCep());
        usuarioPaciente.setLogradouro(usuarioPacienteDTO.getLogradouro());
        usuarioPaciente.setNumero(usuarioPacienteDTO.getNumero());
        usuarioPaciente.setComplemento(usuarioPacienteDTO.getComplemento());
        usuarioPaciente.setBairro(usuarioPacienteDTO.getBairro());
        usuarioPaciente.setCidade(usuarioPacienteDTO.getCidade());
        usuarioPaciente.setUf(usuarioPacienteDTO.getUf());
        usuarioPaciente.setContato(usuarioPacienteDTO.getContato());
        usuarioPaciente.setPontos(usuarioPacienteDTO.getPontos());
        usuarioPaciente.setDescontos(usuarioPacienteDTO.getDescontos());

        return usuarioPacienteRepository.save(usuarioPaciente);
    }


    @Transactional
    public void deleteUsuario(Long id) {
        usuarioPacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o ID: " + id));

        usuarioPacienteRepository.deleteById(id);
    }
}






