package com.example.SmartoothAI.controller;

import com.example.SmartoothAI.dto.PlanoDTO;
import com.example.SmartoothAI.dto.UsuarioPacienteDTO;
import com.example.SmartoothAI.services.PlanoService;
import com.example.SmartoothAI.services.UsuarioPacienteService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UsuarioPacienteController {

    private final UsuarioPacienteService usuarioPacienteService;
    private final PlanoService planoService;

    // 🔹 Obtém o ID do usuário logado
    private Long getUsuarioLogadoId(HttpSession session) {
        Object usuarioId = session.getAttribute("usuarioLogadoId");
        if (usuarioId instanceof Long) {
            return (Long) usuarioId;
        }
        return null;
    }

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
            System.out.println("✅ Login bem-sucedido! Redirecionando para home...");
            return "redirect:/home";
        } else {

            model.addAttribute("error", "Credenciais inválidas");
            return "auth/login";
        }
    }


    @GetMapping("/home")
    public String showHomePage(HttpSession session, Model model) {
        Long usuarioId = getUsuarioLogadoId(session);


        if (usuarioId == null) {
            return "redirect:/login";
        }

        List<PlanoDTO> planos = planoService.getPlanosByUsuarioId(usuarioId);
        model.addAttribute("planos", planos);
        model.addAttribute("mensagem", planos.isEmpty() ? "Não há plano cadastrado." : "");

        return "auth/home";
    }


    // 🔹 Exibe formulário de cadastro
    @GetMapping("/cadastro")
    public String showCadastroForm(Model model) {
        model.addAttribute("usuario", new UsuarioPacienteDTO());
        return "auth/cadastro";
    }


    // 🔹 Processa cadastro de novo usuário
    @PostMapping("/cadastro")
    public String cadastrarUsuario(@ModelAttribute("usuario") UsuarioPacienteDTO usuarioPacienteDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "auth/cadastro";
        }

        try {
            usuarioPacienteService.createUsuario(usuarioPacienteDTO);
        } catch (Exception e) {
            return "auth/cadastro";
        }

        return "redirect:/login"; // Corrigido para um caminho absoluto
    }


    // 🔹 Exibe formulário de edição do usuário logado
    @GetMapping("/editar/{id}")
    public String showEditForm(HttpSession session, Model model) {
        Long usuarioId = getUsuarioLogadoId(session);
        System.out.println("Usuário logado ID: " + usuarioId);

        if (usuarioId == null) {
            return "redirect:auth/login";
        }

        UsuarioPacienteDTO usuario = usuarioPacienteService.getUsuarioPacienteById(usuarioId);
        model.addAttribute("usuario", usuario);
        return "usuario-paciente/editar-usuario";
    }

    // 🔹 Atualiza o usuário logado
    @PutMapping("/editar/{id}")
    public String updateUsuario(@PathVariable Long id, @Valid @ModelAttribute("usuario") UsuarioPacienteDTO usuario, BindingResult bindingResult, HttpSession session, Model model) {

        if (bindingResult.hasErrors()) {
            return "usuario-paciente/editar-usuario"; // Se houver erros, retorne para o formulário de edição
        }

        Long usuarioId = getUsuarioLogadoId(session);
        if (!usuarioId.equals(id)) {
            return "redirect:auth/home";  // Redireciona caso a edição não seja para o usuário logado
        }

        // Atualiza o usuário no banco de dados
        usuarioPacienteService.updateUsuario(id, usuario);

        model.addAttribute("successMessage", "Perfil atualizado com sucesso!");

        // Redireciona para a página de perfil ou home
        return "redirect:auth/home"; // Ou poderia ser "redirect:/editar/{id}" se quiser mostrar o perfil editado
    }




    // 🔹 Exclui o usuário logado
    @PostMapping("/deletar")
    public String deleteUsuario(HttpSession session) {
        Long usuarioId = getUsuarioLogadoId(session);

        if (usuarioId != null) {
            usuarioPacienteService.deleteUsuario(usuarioId);
            session.invalidate(); // Remove a sessão após a exclusão
        }

        return "redirect:auth/cadastro";
    }

    // 🔹 Logout: Remove a sessão do usuário
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Invalida a sessão
        return "redirect:/login";
    }
}
