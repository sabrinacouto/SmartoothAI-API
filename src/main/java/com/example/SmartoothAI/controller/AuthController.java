package com.example.SmartoothAI.controller;

import com.example.SmartoothAI.config.JwtUtil;
import com.example.SmartoothAI.dto.AuthRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authentictionManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public String autenticarUsuario(@RequestBody AuthRequest authRequest) {
        //faco a autenticacao
        authentictionManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUserName(),authRequest.getPassword()));
        //carregar dados do usuario
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUserName());
        //gero token JWT
        return jwtUtil.generateToken(userDetails.getUsername());
    }
}
