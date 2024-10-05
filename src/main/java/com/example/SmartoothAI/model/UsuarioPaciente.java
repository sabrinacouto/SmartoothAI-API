package com.example.SmartoothAI.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_usuario_paciente")
public class UsuarioPaciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_paciente_id")
    private Long usuarioPacienteId;

    private String nome;
    private String sobrenome;
    private String email;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_nasc")
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

