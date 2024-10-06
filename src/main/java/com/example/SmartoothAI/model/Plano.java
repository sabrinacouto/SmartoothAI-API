package com.example.SmartoothAI.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_plano")
public class Plano {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plano_seq")
    @SequenceGenerator(name = "plano_seq", sequenceName = "seq_plano_id", allocationSize = 1)
    @Column(name = "plano_id")
    private Long planoId;

    private String descricao;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_inicio")
    private Date dataInicio;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_fim")
    private Date dataFim;

    private Double valor;
}

