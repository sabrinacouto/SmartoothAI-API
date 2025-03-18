package com.example.SmartoothAI.controller;

import com.example.SmartoothAI.dto.UsuarioPacienteDTO;
import com.example.SmartoothAI.services.UsuarioPacienteService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
@RequiredArgsConstructor
public class LoginController {

    private final UsuarioPacienteService usuarioPacienteService;

    // 🔹 Exibe formulário de login
    @GetMapping("/login")
    public String showLoginForm() {
        return "auth/login";
    }

    // 🔹 Processa login e armazena ID na sessão
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String senha, Model model, HttpSession session) {
        UsuarioPacienteDTO usuario = usuarioPacienteService.authenticateUser(email, senha);

        if (usuario != null) {
            session.setAttribute("usuarioLogadoId", usuario.getPacienteId());
            System.out.println("✅ Login bem-sucedido! ID do usuário: " + usuario.getPacienteId());
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Credenciais inválidas");
            return "auth/login";
        }
    }


    // 🔹 Logout: Remove a sessão do usuário
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Invalida a sessão
        return "redirect:/auth/login";
    }
}

