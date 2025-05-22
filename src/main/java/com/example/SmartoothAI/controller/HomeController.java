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

    private Long getUsuarioLogadoId(HttpSession session) {
        Object usuarioId = session.getAttribute("usuarioLogadoId");
        if (usuarioId instanceof Long) {
            return (Long) usuarioId;
        }
        return null;
    }

    @GetMapping("/")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/home")
    public String showUserHome(HttpSession session, Model model) {
        Long usuarioId = getUsuarioLogadoId(session);

        if (usuarioId != null) {

            UsuarioPacienteDTO usuario = usuarioPacienteService.getUsuarioPacienteById(usuarioId);
            model.addAttribute("usuario", usuario);

            List<PlanoDTO> planos = planoService.getPlanosByUsuarioId(usuarioId);
            model.addAttribute("planos", planos);
            model.addAttribute("mensagem", planos.isEmpty() ? "Não há plano cadastrado." : "");
        } else {

            model.addAttribute("erro", "Usuário não logado.");
            model.addAttribute("planos", List.of());
        }

        return "auth/home";
    }

}
