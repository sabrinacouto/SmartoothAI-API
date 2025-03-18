package com.example.SmartoothAI.controller;

import com.example.SmartoothAI.dto.PlanoDTO;
import com.example.SmartoothAI.services.PlanoService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping("/planos")
@RequiredArgsConstructor
public class PlanoController {

    private final PlanoService planoService;

    // 🔹 Método para obter o ID do paciente logado da sessão
    private Long getUsuarioLogadoId(HttpSession session) {
        Object usuarioId = session.getAttribute("usuarioLogadoId");
        if (usuarioId instanceof Long) {
            return (Long) usuarioId;
        }
        return null;
    }

    @GetMapping("/cadastro")
    public String showCadastroForm(Model model, HttpSession session) {
        // 🔹 Recupera o ID do paciente logado da sessão
        Long usuarioId = getUsuarioLogadoId(session);
        if (usuarioId == null) {
            return "redirect:/login"; // Redireciona se o paciente não estiver logado
        }

        // 🔹 Cria o PlanoDTO e associa o ID do paciente
        PlanoDTO planoDTO = new PlanoDTO();
        planoDTO.setUsuarioPacienteId(usuarioId); // Associa automaticamente o ID do paciente
        model.addAttribute("tiposPlano", Arrays.asList("Básico", "Premium", "Executivo"));  // Se necessário para o select
        model.addAttribute("planoDTO", planoDTO); // Envia o planoDTO para o modelo
        return "plano/cadastro-plano"; // Retorna a página do formulário de cadastro
    }


    @PostMapping("/cadastro")
    public String createPlano(@ModelAttribute("planoDTO") PlanoDTO planoDTO, HttpSession session) {
        Long usuarioId = getUsuarioLogadoId(session);
        if (usuarioId == null) {
            return "redirect:/login"; // Redireciona para login se o usuário não estiver logado
        }

        planoDTO.setUsuarioPacienteId(usuarioId); // Associa automaticamente o ID do usuário logado
        planoService.createPlano(planoDTO); // Chama o serviço para salvar o plano

        return "redirect:/home"; // Redireciona para a página inicial
    }




    @GetMapping("/{id}/editar")
    public String showEditForm(@PathVariable Long id, Model model) {
        PlanoDTO planoDTO = planoService.getPlanoById(id);

        // Verificar se o planoDTO é null
        if (planoDTO == null) {
            System.out.println("Plano não encontrado com o ID: " + id);
            return "redirect:/home";
        }
        model.addAttribute("tiposPlano", Arrays.asList("Básico", "Premium", "Executivo"));
        model.addAttribute("planoDTO", planoDTO);
        return "plano/editar-plano"; // Retorna o formulário de edição
    }



    @PutMapping("/{id}/editar")
    public String updatePlano(@PathVariable Long id, @ModelAttribute("planoDTO") PlanoDTO planoDTO, HttpSession session) {
        Long usuarioId = getUsuarioLogadoId(session);
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