package com.example.SmartoothAI.controller;

import com.example.SmartoothAI.dto.PlanoDTO;
import com.example.SmartoothAI.services.PlanoService;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


@Controller
@RequestMapping("/planos")
@RequiredArgsConstructor
public class PlanoController {

    private final PlanoService planoService;

    @GetMapping("/cadastro")
    public String showCadastroForm(Model model) {
        model.addAttribute("planoDTO", new PlanoDTO());
        return "cadastro-plano";
    }

    @PostMapping("/cadastro")
    public String createPlano(@ModelAttribute("planoDTO") PlanoDTO planoDTO) {
        planoService.createPlano(planoDTO);
        return "redirect:/home";
    }


    @GetMapping("/{id}/editar")
    public String showEditForm(@PathVariable Long id, Model model) {
        PlanoDTO planoDTO = planoService.getPlanoById(id);
        if (planoDTO != null) {
            model.addAttribute("planoDTO", planoDTO);
            return "editar-plano";
        }
        return "redirect:/home";
    }

    @PostMapping("/{id}/editar")
    public String updatePlano(@PathVariable Long id, @ModelAttribute("planoDTO") PlanoDTO planoDTO) {
        planoService.updatePlano(id, planoDTO);
        return "redirect:/home";
    }

    @GetMapping("/{id}/excluir")
    public String deletePlano(@PathVariable Long id) {
        planoService.deletePlano(id);
        return "redirect:/home";
    }
}


