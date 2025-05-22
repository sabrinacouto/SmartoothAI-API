package com.example.SmartoothAI.services;

import com.example.SmartoothAI.model.UsuarioPaciente;
import com.example.SmartoothAI.repository.UsuarioPacienteRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioPacienteRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioPacienteRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioPaciente usuario = usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return User.withUsername(usuario.getEmail())
                .password(usuario.getSenha())
                .roles("USUARIO")
                .build();
    }
}
