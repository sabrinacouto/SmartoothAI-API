package com.example.SmartoothAI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfissionalDTO {

    private Long profissionalId;
    private String nome;
    private String email;
    private String especialidade;
    private String experiencia;
    private String contato;
    private String senha;
}
