package com.example.SmartoothAI.controller;

import com.example.SmartoothAI.dto.ProfissionalDTO;
import com.example.SmartoothAI.exceptions.EmailAlreadyExistsException;
import com.example.SmartoothAI.services.ProfissionalService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profissional")
@RequiredArgsConstructor
public class ProfissionalController {

    private final ProfissionalService profissionalService;

    private Long getUsuarioLogadoId(HttpSession session) {
        Object usuarioId = session.getAttribute("usuarioLogadoId");
        if (usuarioId instanceof Long) {
            return (Long) usuarioId;
        }
        return null;
    }

    @GetMapping("/cadastro")
    public String showCadastroForm(Model model) {
        model.addAttribute("profissional", new ProfissionalDTO());
        return "auth/form-register-profissional";
    }

    @PostMapping("/cadastro")
    public String cadastrarProfissional(@ModelAttribute("profissional") @Valid ProfissionalDTO profissionalDTO,
                                        BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "auth/form-register-profissional";
        }

        try {
            profissionalService.createProfissional(profissionalDTO);
            model.addAttribute("success", "Profissional cadastrado com sucesso!");
        } catch (EmailAlreadyExistsException | IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "auth/form-register-profissional";
        } catch (Exception e) {
            model.addAttribute("error", "Erro inesperado. Tente novamente mais tarde.");
            return "auth/form-register-profissional";
        }

        return "auth/form-register-profissional";
    }

    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable Long id, HttpSession session, Model model) {
        Long usuarioId = getUsuarioLogadoId(session);
        if (usuarioId != null) {
            ProfissionalDTO profissional = profissionalService.getProfissionalById(usuarioId);
            model.addAttribute("profissional", profissional);
        } else {
            model.addAttribute("erro", "Profissional não logado.");
        }

        ProfissionalDTO profissional = profissionalService.getProfissionalById(id);
        if (profissional == null) {
            return "redirect:/login";
        }
        model.addAttribute("profissional", profissional);
        return "profissional/editar-profissional";
    }

    @PatchMapping("/editar/{id}")
    public String editarProfissional(@PathVariable("id") Long id,
                                     @ModelAttribute("profissional") ProfissionalDTO profissionalDTO,
                                     Model model, HttpSession session) {
        Long usuarioId = getUsuarioLogadoId(session);
        if (usuarioId != null) {
            ProfissionalDTO profissional = profissionalService.getProfissionalById(usuarioId);
            model.addAttribute("profissional", profissional);
        } else {
            model.addAttribute("erro", "Profissional não logado.");
        }

        try {
            profissionalService.updateProfissional(id, profissionalDTO);
            return "redirect:/home";
        } catch (EmailAlreadyExistsException e) {
            model.addAttribute("error", e.getMessage());
            return "profissional/editar-profissional";
        } catch (Exception e) {
            model.addAttribute("error", "Erro inesperado. Tente novamente mais tarde.");
            return "profissional/editar-profissional";
        }
    }

    @DeleteMapping("/deletar/{id}")
    public String deleteProfissional(@PathVariable("id") Long id, HttpSession session, Model model) {
        Long usuarioId = getUsuarioLogadoId(session);

        if (usuarioId != null) {
            ProfissionalDTO profissional = profissionalService.getProfissionalById(usuarioId);
            model.addAttribute("profissional", profissional);

            profissionalService.deleteProfissional(usuarioId);
            session.invalidate();
        } else {
            model.addAttribute("error", "Profissional não logado.");
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}

