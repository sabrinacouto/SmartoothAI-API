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
@RequestMapping()
@RequiredArgsConstructor
public class UsuarioPacienteController {

    private final UsuarioPacienteService usuarioPacienteService;
    private final PlanoService planoService;

    // 🔹 Obtém o ID do usuário logado
    private Long getUsuarioLogadoId(HttpSession session) {
        Object usuarioId = session.getAttribute("usuarioLogadoId");
        if (usuarioId instanceof Long) {
            System.out.println("ID do usuário logado: " + usuarioId);  // Adicionando log
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
            System.out.println("✅ Login bem-sucedido! ID do usuário: " + usuario.getPacienteId());
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Credenciais inválidas");
            return "auth/login";
        }
    }



    @GetMapping("/home")
    public String showHomePage(HttpSession session, Model model) {
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



    @GetMapping("/editarUsuario/{id}")
    public String showEditForm(@PathVariable Long id, HttpSession session, Model model) {
        Long usuarioId = getUsuarioLogadoId(session);
        if (usuarioId != null) {
            UsuarioPacienteDTO usuario = usuarioPacienteService.getUsuarioPacienteById(usuarioId);
            model.addAttribute("usuario", usuario);
        } else {
            model.addAttribute("erro", "Usuário não logado.");
        }

        UsuarioPacienteDTO usuario = usuarioPacienteService.getUsuarioPacienteById(id);
        if (usuario == null) {
            return "redirect:/login"; // Ou qualquer outra lógica para lidar com a falha
        }
        model.addAttribute("usuario", usuario);
        return "usuario-paciente/editar-usuario"; // Caminho correto para o template
    }


    // 🔹 Atualiza o usuário logado

    @RequestMapping(value = "/editarUsuario/{id}", method = RequestMethod.PATCH)
    public String editarUsuario(@PathVariable("id") Long id, UsuarioPacienteDTO usuarioPacienteDTO, Model model, HttpSession session ) {
        Long usuarioId = getUsuarioLogadoId(session);
        if (usuarioId != null) {
            UsuarioPacienteDTO usuario = usuarioPacienteService.getUsuarioPacienteById(usuarioId);
            model.addAttribute("usuario", usuario);
        } else {
            model.addAttribute("erro", "Usuário não logado.");
        }
        try {
            usuarioPacienteService.updateUsuario(id, usuarioPacienteDTO);
            return "redirect:/home";  // Após editar, redireciona para a página de home
        } catch (Exception e) {
            // Se houver erro, exibe mensagem de erro
            return "usuario-paciente/editar-usuario";
        }
    }



    // 🔹 Exclui o usuário logado
    @DeleteMapping("/deletarUsuario/{id}")
    public String deleteUsuario(HttpSession session) {
        Long usuarioId = getUsuarioLogadoId(session);

        if (usuarioId != null) {
            usuarioPacienteService.deleteUsuario(usuarioId);
            session.invalidate(); // Remove a sessão após a exclusão
        }

        return "redirect:/cadastro";
    }

    // 🔹 Logout: Remove a sessão do usuário
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Invalida a sessão
        return "redirect:/login";
    }
}
