package com.example.SmartoothAI.controller;

import com.example.SmartoothAI.config.GroqConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

    @Controller
    public class PlanoRecomendacaoController {

        @Autowired
        private GroqConfig groqService;

        @GetMapping("/recomendar-planos")
        public String exibirFormulario() {
            return "plano/recomendar-planos";
        }

        @PostMapping("/recomendar-planos")
        public String recomendarPlanos(@RequestParam String desejo, Model model) {
            String resposta = groqService.gerarResposta("Usuário deseja: " + desejo + ". Recomende planos odontológicos.");
            model.addAttribute("recomendacao", resposta);
            model.addAttribute("desejo", desejo);
            return "plano/recomendar-planos";
        }
    }

