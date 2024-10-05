package com.example.SmartoothAI.dto;

import lombok.Data;
import java.util.Date;

@Data
public class UsuarioPacienteDTO {
    private Long usuarioPacienteId;
    private String nome;
    private String sobrenome;
    private String email;
    private Date dataNasc;
    private String genero;
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String contato;
    private Double pontos;
    private Double descontos;
}

