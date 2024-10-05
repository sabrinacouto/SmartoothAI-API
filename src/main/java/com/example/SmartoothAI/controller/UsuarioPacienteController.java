package com.example.SmartoothAI.controller;

import com.example.SmartoothAI.dto.UsuarioPacienteDTO;
import com.example.SmartoothAI.exceptions.EmailAlreadyExistsException;
import com.example.SmartoothAI.exceptions.ResourceNotFoundException;
import com.example.SmartoothAI.model.UsuarioPaciente;
import com.example.SmartoothAI.services.UsuarioPacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioPacienteController {

    private final UsuarioPacienteService usuarioPacienteService;

    public UsuarioPacienteController(UsuarioPacienteService usuarioPacienteService) {
        this.usuarioPacienteService = usuarioPacienteService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioPaciente>> getAllUsuarios() {
        List<UsuarioPaciente> usuarios = usuarioPacienteService.getAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioPaciente> getUsuarioById(@PathVariable Long id) {
        UsuarioPaciente usuario = usuarioPacienteService.getUsuarioById(id);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<UsuarioPaciente> createUsuario(@RequestBody UsuarioPacienteDTO usuarioPacienteDTO) {
        UsuarioPaciente novoUsuario = usuarioPacienteService.createUsuario(usuarioPacienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioPaciente> updateUsuario(@PathVariable Long id,
                                                         @RequestBody UsuarioPacienteDTO usuarioPacienteDTO) {
        UsuarioPaciente usuarioAtualizado = usuarioPacienteService.updateUsuario(id, usuarioPacienteDTO);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioPacienteService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}





