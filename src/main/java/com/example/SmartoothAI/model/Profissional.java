package com.example.SmartoothAI.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_profissional")
public class Profissional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profissional_id")
    private Long profissionalId;

    private String nome;
    private String sobrenome;
    private String email;
    private String contato;
    private String registroConselho;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_registro")
    private Date dataRegistro;
}
