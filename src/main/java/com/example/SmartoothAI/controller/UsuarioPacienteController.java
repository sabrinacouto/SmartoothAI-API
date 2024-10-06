package com.example.SmartoothAI.controller;

import com.example.SmartoothAI.dto.UsuarioPacienteDTO;
import com.example.SmartoothAI.model.UsuarioPaciente;
import com.example.SmartoothAI.services.UsuarioPacienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jakarta.validation.Valid;

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
    public ResponseEntity<String> createUsuario(@Valid @RequestBody UsuarioPacienteDTO usuarioPacienteDTO) {
        return usuarioPacienteService.createUsuario(usuarioPacienteDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUsuario(@PathVariable Long id,
                                                @Valid @RequestBody UsuarioPacienteDTO usuarioPacienteDTO) {
        return usuarioPacienteService.updateUsuario(id, usuarioPacienteDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Long id) {
        return usuarioPacienteService.deleteUsuario(id);
    }

}






