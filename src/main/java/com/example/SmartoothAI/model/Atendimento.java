package com.example.SmartoothAI.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_atendimento")
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "atendimento_id")
    private Long atendimentoId;

    private String descricao;

    @Temporal(TemporalType.DATE)
    @Column(name = "data")
    private Date data;

    @Temporal(TemporalType.TIME)
    @Column(name = "hora")
    private Date hora;

    @Column(name = "incluso_plano")
    private char inclusoPlano; // armaneza somente um único caractere, por isso char

    @Column(name = "custo")
    private Double custo;

    @ManyToOne
    @JoinColumn(name = "paciente_id", referencedColumnName = "paciente_id")
    private UsuarioPaciente usuarioPaciente;

    @ManyToOne
    @JoinColumn(name = "profissional_id", referencedColumnName = "profissional_id")
    private Profissional profissional;

    @ManyToOne
    @JoinColumn(name = "recomendacao_trat_id", referencedColumnName = "recomendacao_id")
    private RecomendacaoTrat recomendacaoTrat;
}

