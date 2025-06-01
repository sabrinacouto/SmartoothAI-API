package com.example.SmartoothAI.controller;

import com.example.SmartoothAI.dto.PlanoDTO;
import com.example.SmartoothAI.model.UsuarioPaciente;
import com.example.SmartoothAI.repository.UsuarioPacienteRepository;
import com.example.SmartoothAI.services.PlanoService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;

@Controller
@RequestMapping("/planos")
@RequiredArgsConstructor
public class PlanoController {

    private final PlanoService planoService;
    private final UsuarioPacienteRepository usuarioPacienteRepository;

    private Long getUsuarioLogadoId(HttpSession session) {
        Object usuarioId = session.getAttribute("usuarioLogadoId");
        if (usuarioId instanceof Long) {
            return (Long) usuarioId;
        }
        return null;
    }

    private Long getUsuarioLogadoId(UserDetails userDetails) {
        if (userDetails == null) return null;

        Optional<UsuarioPaciente> usuarioOpt = usuarioPacienteRepository.findByEmail(userDetails.getUsername());
        return usuarioOpt.map(UsuarioPaciente::getPacienteId).orElse(null);
    }


    @GetMapping("/cadastro")
    public String showCadastroForm(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Long usuarioId = getUsuarioLogadoId(userDetails);
        if (usuarioId == null) {
            return "redirect:/login";
        }

        PlanoDTO planoDTO = new PlanoDTO();
        planoDTO.setUsuarioPacienteId(usuarioId);
        model.addAttribute("tiposPlano", Arrays.asList("Básico", "Premium", "Executivo"));
        model.addAttribute("planoDTO", planoDTO);
        return "plano/cadastro-plano";
    }



    @PostMapping("/cadastro")
    public String createPlano(@ModelAttribute("planoDTO") PlanoDTO planoDTO,
                              @AuthenticationPrincipal UserDetails userDetails) {
        Long usuarioId = getUsuarioLogadoId(userDetails);
        if (usuarioId == null) {
            return "redirect:/login";
        }

        planoDTO.setUsuarioPacienteId(usuarioId);
        planoService.createPlano(planoDTO);

        return "redirect:/home";
    }




    @GetMapping("/{id}/editar")
    public String showEditForm(@PathVariable Long id, Model model) {
        PlanoDTO planoDTO = planoService.getPlanoById(id);

        if (planoDTO == null) {
            System.out.println("Plano não encontrado com o ID: " + id);
            return "redirect:/home";
        }
        model.addAttribute("tiposPlano", Arrays.asList("Básico", "Premium", "Executivo"));
        model.addAttribute("planoDTO", planoDTO);
        return "plano/editar-plano";
    }


    @PutMapping("/{id}/editar")
    public String updatePlano(@PathVariable Long id,
                              @ModelAttribute("planoDTO") PlanoDTO planoDTO,
                              @AuthenticationPrincipal UserDetails userDetails) {
        Long usuarioId = getUsuarioLogadoId(userDetails);
        if (usuarioId == null) {
            return "redirect:/login";
        }

        planoDTO.setUsuarioPacienteId(usuarioId);
        planoService.updatePlano(id, planoDTO);

        return "redirect:/home";
    }


    @DeleteMapping("/{id}/excluir")
    public String deletePlano(@PathVariable Long id) {
        planoService.deletePlano(id);
        return "redirect:/home";
    }


}