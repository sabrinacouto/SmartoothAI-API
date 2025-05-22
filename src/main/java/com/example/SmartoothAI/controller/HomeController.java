package com.example.SmartoothAI.controller;

import com.example.SmartoothAI.dto.PlanoDTO;
import com.example.SmartoothAI.dto.UsuarioPacienteDTO;
import com.example.SmartoothAI.model.UsuarioPaciente;
import com.example.SmartoothAI.repository.UsuarioPacienteRepository;
import com.example.SmartoothAI.services.PlanoService;
import com.example.SmartoothAI.services.UsuarioPacienteService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping()
@RequiredArgsConstructor
public class HomeController {

    private final UsuarioPacienteService usuarioPacienteService;
    private final PlanoService planoService;
    private final UsuarioPacienteRepository usuarioPacienteRepository;

    private Long getUsuarioLogadoId(HttpSession session) {
        Object usuarioId = session.getAttribute("usuarioLogadoId");
        if (usuarioId != null) {
            return Long.valueOf(usuarioId.toString());
        }
        return null;
    }


    @PostMapping("/login")
    public String processLogin(@RequestParam String email,
                               @RequestParam String senha,
                               HttpSession session,
                               Model model) {

        UsuarioPacienteDTO usuario = usuarioPacienteService.authenticateUser(email, senha);

        if (usuario != null) {

            session.setAttribute("usuarioLogadoId", usuario.getPacienteId());

            return "redirect:/home";
        } else {
            model.addAttribute("erro", "E-mail ou senha inválidos.");
            return "auth/login";
        }
    }



    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }
    @GetMapping("/home")
    public String showUserHome(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails == null) {
            return "redirect:/login";
        }

        String email = userDetails.getUsername();
        Optional<UsuarioPaciente> usuarioOpt = usuarioPacienteRepository.findByEmail(email);

        if (usuarioOpt.isEmpty()) {
            return "redirect:/login";
        }

        UsuarioPaciente usuario = usuarioOpt.get();

        model.addAttribute("usuario", usuario);

        List<PlanoDTO> planos = planoService.getPlanosByUsuarioId(usuario.getPacienteId());
        model.addAttribute("planos", planos);
        model.addAttribute("mensagem", planos.isEmpty() ? "Não há plano cadastrado." : "");

        return "auth/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }



}
