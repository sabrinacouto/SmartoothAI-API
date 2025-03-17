package com.example.SmartoothAI.services;

import com.example.SmartoothAI.dto.UsuarioPacienteDTO;
import com.example.SmartoothAI.exceptions.EmailAlreadyExistsException;
import com.example.SmartoothAI.exceptions.ResourceNotFoundException;
import com.example.SmartoothAI.model.UsuarioPaciente;
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
        System.out.println("Usuário salvo no banco!");
    }

    @Transactional
    public void updateUsuario(Long id, UsuarioPacienteDTO usuarioPacienteDTO) {
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
        usuarioPaciente.setDescontos(usuarioPacienteDTO.getDescontos());
        usuarioPaciente.setSenha(usuarioPacienteDTO.getSenha());

        usuarioPacienteRepository.save(usuarioPaciente);
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
            // Se o email existir e a senha for correta, retorna os dados do usuário
            UsuarioPaciente usuarioPaciente = usuario.get();
            UsuarioPacienteDTO usuarioDTO = new UsuarioPacienteDTO();
            usuarioDTO.setPacienteId(usuarioPaciente.getPacienteId());
            usuarioDTO.setNome(usuarioPaciente.getNome());
            usuarioDTO.setEmail(usuarioPaciente.getEmail());
            // Adicione mais campos conforme necessário
            return usuarioDTO;
        }
        return null;
    }

}
