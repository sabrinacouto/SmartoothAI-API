package com.example.SmartoothAI.services;

import com.example.SmartoothAI.dto.UsuarioPacienteDTO;
import com.example.SmartoothAI.exceptions.EmailAlreadyExistsException;
import com.example.SmartoothAI.exceptions.ResourceNotFoundException;
import com.example.SmartoothAI.model.UsuarioPaciente;
import com.example.SmartoothAI.repository.PlanoRepository;
import com.example.SmartoothAI.repository.UsuarioPacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioPacienteService {

    private final UsuarioPacienteRepository usuarioPacienteRepository;
    private final PlanoRepository planoRepository;


    private UsuarioPacienteDTO convertToDTO(UsuarioPaciente usuarioPaciente) {
        UsuarioPacienteDTO dto = new UsuarioPacienteDTO();
        dto.setPacienteId(usuarioPaciente.getPacienteId());
        dto.setNome(usuarioPaciente.getNome());
        dto.setSobrenome(usuarioPaciente.getSobrenome());
        dto.setEmail(usuarioPaciente.getEmail());
        dto.setDataNasc(usuarioPaciente.getDataNasc());
        dto.setGenero(usuarioPaciente.getGenero());
        dto.setCep(usuarioPaciente.getCep());
        dto.setLogradouro(usuarioPaciente.getLogradouro());
        dto.setNumero(usuarioPaciente.getNumero());
        dto.setComplemento(usuarioPaciente.getComplemento());
        dto.setBairro(usuarioPaciente.getBairro());
        dto.setCidade(usuarioPaciente.getCidade());
        dto.setUf(usuarioPaciente.getUf());
        dto.setContato(usuarioPaciente.getContato());
        dto.setDescontos(usuarioPaciente.getDescontos());
        dto.setSenha(usuarioPaciente.getSenha());
        return dto;
    }

    @Transactional
    public void createUsuario(UsuarioPacienteDTO usuarioPacienteDTO) {
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
        usuarioPaciente.setDescontos(usuarioPacienteDTO.getDescontos());
        usuarioPaciente.setSenha(usuarioPacienteDTO.getSenha());

        usuarioPacienteRepository.save(usuarioPaciente);
    }

    @Transactional
    public void updateUsuario(Long id, UsuarioPacienteDTO usuarioPacienteDTO) {

        UsuarioPaciente usuarioPaciente = usuarioPacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o ID: " + id));


        if (!usuarioPaciente.getEmail().equals(usuarioPacienteDTO.getEmail()) &&
                usuarioPacienteRepository.findByEmail(usuarioPacienteDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Já existe um usuário com este e-mail.");
        }
        if (usuarioPacienteDTO.getNome() != null && !usuarioPacienteDTO.getNome().equals(usuarioPaciente.getNome())) {
            usuarioPaciente.setNome(usuarioPacienteDTO.getNome());
        }
        if (usuarioPacienteDTO.getSobrenome() != null && !usuarioPacienteDTO.getSobrenome().equals(usuarioPaciente.getSobrenome())) {
            usuarioPaciente.setSobrenome(usuarioPacienteDTO.getSobrenome());
        }
        if (usuarioPacienteDTO.getEmail() != null && !usuarioPacienteDTO.getEmail().equals(usuarioPaciente.getEmail())) {
            usuarioPaciente.setEmail(usuarioPacienteDTO.getEmail());
        }
        if (usuarioPacienteDTO.getDataNasc() != null && !usuarioPacienteDTO.getDataNasc().equals(usuarioPaciente.getDataNasc())) {
            usuarioPaciente.setDataNasc(usuarioPacienteDTO.getDataNasc());
        }
        if (usuarioPacienteDTO.getGenero() != null && !usuarioPacienteDTO.getGenero().equals(usuarioPaciente.getGenero())) {
            usuarioPaciente.setGenero(usuarioPacienteDTO.getGenero());
        }
        if (usuarioPacienteDTO.getCep() != null && !usuarioPacienteDTO.getCep().equals(usuarioPaciente.getCep())) {
            usuarioPaciente.setCep(usuarioPacienteDTO.getCep());
        }
        if (usuarioPacienteDTO.getLogradouro() != null && !usuarioPacienteDTO.getLogradouro().equals(usuarioPaciente.getLogradouro())) {
            usuarioPaciente.setLogradouro(usuarioPacienteDTO.getLogradouro());
        }
        if (usuarioPacienteDTO.getNumero() != null && !usuarioPacienteDTO.getNumero().equals(usuarioPaciente.getNumero())) {
            usuarioPaciente.setNumero(usuarioPacienteDTO.getNumero());
        }
        if (usuarioPacienteDTO.getComplemento() != null && !usuarioPacienteDTO.getComplemento().equals(usuarioPaciente.getComplemento())) {
            usuarioPaciente.setComplemento(usuarioPacienteDTO.getComplemento());
        }
        if (usuarioPacienteDTO.getBairro() != null && !usuarioPacienteDTO.getBairro().equals(usuarioPaciente.getBairro())) {
            usuarioPaciente.setBairro(usuarioPacienteDTO.getBairro());
        }
        if (usuarioPacienteDTO.getCidade() != null && !usuarioPacienteDTO.getCidade().equals(usuarioPaciente.getCidade())) {
            usuarioPaciente.setCidade(usuarioPacienteDTO.getCidade());
        }
        if (usuarioPacienteDTO.getUf() != null && !usuarioPacienteDTO.getUf().equals(usuarioPaciente.getUf())) {
            usuarioPaciente.setUf(usuarioPacienteDTO.getUf());
        }
        if (usuarioPacienteDTO.getContato() != null && !usuarioPacienteDTO.getContato().equals(usuarioPaciente.getContato())) {
            usuarioPaciente.setContato(usuarioPacienteDTO.getContato());
        }
        if (usuarioPacienteDTO.getDescontos() != null && !usuarioPacienteDTO.getDescontos().equals(usuarioPaciente.getDescontos())) {
            usuarioPaciente.setDescontos(usuarioPacienteDTO.getDescontos());
        }
        if (usuarioPacienteDTO.getSenha() != null && !usuarioPacienteDTO.getSenha().equals(usuarioPaciente.getSenha())) {
            usuarioPaciente.setSenha(usuarioPacienteDTO.getSenha());
        }

        usuarioPacienteRepository.save(usuarioPaciente);

    }

    public boolean checkUsuarioTemPlanos(Long id) {
        return planoRepository.existsByUsuarioPaciente_PacienteId(id);
    }



    public List<UsuarioPacienteDTO> getAllUsuarios() {
        return usuarioPacienteRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UsuarioPacienteDTO getUsuarioPacienteById(Long id) {
        UsuarioPaciente usuarioPaciente = usuarioPacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o ID: " + id));

        return convertToDTO(usuarioPaciente);
    }

    @Transactional
    public void deleteUsuario(Long id) {
        UsuarioPaciente usuarioPaciente = usuarioPacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o ID: " + id));

        usuarioPacienteRepository.delete(usuarioPaciente);
    }

    public UsuarioPacienteDTO authenticateUser(String email, String senha) {
        Optional<UsuarioPaciente> usuario = usuarioPacienteRepository.findByEmail(email);

        if (usuario.isPresent() && usuario.get().getSenha().equals(senha)) {
            UsuarioPaciente usuarioPaciente = usuario.get();
            UsuarioPacienteDTO usuarioDTO = new UsuarioPacienteDTO();
            usuarioDTO.setPacienteId(usuarioPaciente.getPacienteId());
            usuarioDTO.setNome(usuarioPaciente.getNome());
            usuarioDTO.setEmail(usuarioPaciente.getEmail());
            // Adicionar mais campos conforme necessário
            return usuarioDTO;
        }
        return null;
    }


}
