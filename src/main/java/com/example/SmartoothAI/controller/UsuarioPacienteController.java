package com.example.SmartoothAI.controller;

import com.example.SmartoothAI.dto.PlanoDTO;
import com.example.SmartoothAI.dto.UsuarioPacienteDTO;
import com.example.SmartoothAI.exceptions.EmailAlreadyExistsException;
import com.example.SmartoothAI.services.PlanoService;
import com.example.SmartoothAI.services.UsuarioPacienteService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioPacienteController {

    private final UsuarioPacienteService usuarioPacienteService;

    private Long getUsuarioLogadoId(HttpSession session) {
        Object usuarioId = session.getAttribute("usuarioLogadoId");
        if (usuarioId instanceof Long) {
            return (Long) usuarioId;
        }
        return null;
    }


    @GetMapping("/cadastro")
    public String showCadastroForm(Model model) {
        model.addAttribute("usuario", new UsuarioPacienteDTO());
        return "auth/form-register";
    }


    @PostMapping("/cadastro")
    public String cadastrarUsuario(@ModelAttribute("usuario") UsuarioPacienteDTO usuarioPacienteDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "auth/form-register";
        }

        try {
            usuarioPacienteService.createUsuario(usuarioPacienteDTO);
        } catch (EmailAlreadyExistsException e) {
            model.addAttribute("error", e.getMessage());
            return "auth/form-register";
        } catch (Exception e) {
            model.addAttribute("error", "Erro inesperado. Tente novamente mais tarde.");
            return "auth/form-register";
        }

        return "redirect:/login";
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
            return "redirect:/login";
        }
        model.addAttribute("usuario", usuario);
        return "usuario-paciente/editar-usuario";
    }


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
            return "redirect:/home";
        } catch (EmailAlreadyExistsException e) {
            model.addAttribute("error", e.getMessage());
            return "usuario-paciente/editar-usuario";
        } catch (Exception e) {
            model.addAttribute("error", "Erro inesperado. Tente novamente mais tarde.");
            return "usuario-paciente/editar-usuario";
        }
    }

    @RequestMapping(value = "/deletarUsuario/{id}", method = RequestMethod.DELETE)
    public String deleteUsuario(@PathVariable("id") Long id, HttpSession session, Model model) {
        Long usuarioId = getUsuarioLogadoId(session);

        if (usuarioId != null) {
            UsuarioPacienteDTO usuario = usuarioPacienteService.getUsuarioPacienteById(usuarioId);
            model.addAttribute("usuario", usuario);

            if (usuarioPacienteService.checkUsuarioHasPlans(usuarioId)) {
                model.addAttribute("error", "Você não pode excluir sua conta enquanto tiver planos cadastrados. Exclua seus planos primeiro.");
                return "usuario-paciente/editar-usuario";
            }

            // Se não tiver planos cadastrados, exclui o usuário
            usuarioPacienteService.deleteUsuario(usuarioId);
            session.invalidate(); // Invalida a sessão do usuário
        } else {
            // Se o usuário não estiver logado, exibe uma mensagem de erro
            model.addAttribute("error", "Usuário não logado.");
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
