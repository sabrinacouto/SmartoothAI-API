package com.example.SmartoothAI.controller;

import com.example.SmartoothAI.dto.PlanoDTO;
import com.example.SmartoothAI.dto.UsuarioPacienteDTO;
import com.example.SmartoothAI.services.PlanoService;
import com.example.SmartoothAI.services.UsuarioPacienteService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping()
@RequiredArgsConstructor
public class HomeController {

    private final UsuarioPacienteService usuarioPacienteService;
    private final PlanoService planoService;

    // 🔹 Obtém o ID do usuário logado
    private Long getUsuarioLogadoId(HttpSession session) {
        Object usuarioId = session.getAttribute("usuarioLogadoId");
        if (usuarioId instanceof Long) {
            System.out.println("ID do usuário logado: " + usuarioId);
            return (Long) usuarioId;
        }
        return null;
    }

    // 🔹 Página inicial com botões de login e cadastro
    @GetMapping("/")
    public String showHomePage() {
        return "index";  // Redireciona para a tela inicial
    }

    // 🔹 Página Home do usuário
    @GetMapping("/home")
    public String showUserHome(HttpSession session, Model model) {
        Long usuarioId = getUsuarioLogadoId(session);

        if (usuarioId != null) {
            UsuarioPacienteDTO usuario = usuarioPacienteService.getUsuarioPacienteById(usuarioId);
            model.addAttribute("usuario", usuario);
        } else {
            model.addAttribute("erro", "Usuário não logado.");
        }

        List<PlanoDTO> planos = planoService.getPlanosByUsuarioId(usuarioId);
        model.addAttribute("planos", planos);
        model.addAttribute("mensagem", planos.isEmpty() ? "Não há plano cadastrado." : "");

        return "auth/home";
    }
}
