package com.example.SmartoothAI.controller;

import com.example.SmartoothAI.dto.UsuarioPacienteDTO;
import com.example.SmartoothAI.services.UsuarioPacienteService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
@RequiredArgsConstructor
public class LoginController {

    private final UsuarioPacienteService usuarioPacienteService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String senha, Model model, HttpSession session) {
        // Primeiro tenta autenticar usuário paciente
        UsuarioPacienteDTO usuario = usuarioPacienteService.authenticateUser(email, senha);
        if (usuario != null) {
            session.setAttribute("usuarioLogadoId", usuario.getPacienteId());
            session.setAttribute("role", "USUARIO");
            System.out.println("Login bem-sucedido! Usuário ID: " + usuario.getPacienteId());
            return "redirect:/home";
        }


        // Se nenhum conseguiu autenticar, erro
        model.addAttribute("error", "Credenciais inválidas");
        return "auth/login";
    }

    @GetMapping("/escolha-usuario")
    public String escolherUsuario() {
        return "auth/escolha-usuario";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
