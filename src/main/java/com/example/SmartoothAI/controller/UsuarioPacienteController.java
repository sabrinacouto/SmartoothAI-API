package com.example.SmartoothAI.controller;

import com.example.SmartoothAI.dto.PlanoDTO;
import com.example.SmartoothAI.dto.UsuarioPacienteDTO;
import com.example.SmartoothAI.services.PlanoService;
import com.example.SmartoothAI.services.UsuarioPacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/home")
    public String showHomePage(Model model) {
        Long usuarioId = getUsuarioLogadoId();

        List<PlanoDTO> planos = planoService.getPlanosByUsuarioId(usuarioId);

        if (planos.isEmpty()) {
            model.addAttribute("mensagem", "Não há plano cadastrado.");
        } else {
            model.addAttribute("planos", planos);
        }

        return "home";
    }


    private Long getUsuarioLogadoId() {
        // Aqui você pode implementar a lógica para pegar o ID do usuário logado.
        return 1L;  // Exemplo fictício
    }



    // Exibe a página de cadastro de usuário
    @GetMapping("/cadastro")
    public String showCadastroForm(Model model) {
        model.addAttribute("usuario", new UsuarioPacienteDTO());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrarUsuario(@ModelAttribute("usuario") UsuarioPacienteDTO usuarioPacienteDTO, BindingResult bindingResult) {
        // Verificar se há erros de validação
        if (bindingResult.hasErrors()) {
            // Logando erros
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println("Erro: " + error.getDefaultMessage());
            });
            return "cadastro";  // Retorna para o formulário se houver erro
        }

        // Log para ver os dados recebidos
        System.out.println("Dados recebidos: " + usuarioPacienteDTO);

        try {
            usuarioPacienteService.createUsuario(usuarioPacienteDTO);
        } catch (Exception e) {
            // Logando erro no serviço
            System.out.println("Erro ao salvar no banco: " + e.getMessage());
            return "cadastro";  // Retorna para o formulário se ocorrer algum erro ao salvar
        }

        // Redireciona para a página de login após sucesso
        return "redirect:/login";
    }



    // Exibe a página de editar usuário
    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        UsuarioPacienteDTO usuario = usuarioPacienteService.getUsuarioPacienteById(id);
        model.addAttribute("usuario", usuario);
        return "editar-usuario";
    }

    @PostMapping("/editar/{id}")
    public String updateUsuario(@PathVariable("id") Long id, @Valid @ModelAttribute("usuario") UsuarioPacienteDTO usuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editar-usuario";
        }

        usuarioPacienteService.updateUsuario(id, usuario);
        return "redirect:/home";
    }

    // Exclui o usuário
    @PostMapping("/deletar/{id}")
    public String deleteUsuario(@PathVariable("id") Long id) {
        usuarioPacienteService.deleteUsuario(id);
        return "redirect:/cadastro";  // Redireciona para a tela de cadastro após a exclusão
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // Processa o login
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String senha, Model model) {
        UsuarioPacienteDTO usuario = usuarioPacienteService.authenticateUser(email, senha);

        if (usuario != null) {
            // Se as credenciais estiverem corretas, redireciona para a home
            return "redirect:/home";
        } else {
            // Se não encontrar o usuário ou a senha estiver errada, exibe um erro
            model.addAttribute("error", "Credenciais inválidas");
            return "login"; // Retorna para o formulário de login com erro
        }
    }

}
