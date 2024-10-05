package com.example.SmartoothAI.dto;


import lombok.Data;
import java.util.Date;

@Data
public class ProfissionalDTO {

    private Long profissionalId;
    private String nome;
    private String especialidade;
    private String experiencia;
    private String contato;
    private Date dataRegistro;
}
