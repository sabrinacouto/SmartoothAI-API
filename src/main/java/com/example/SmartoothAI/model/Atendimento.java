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
    private Long atendimentoId;

    private String descricao;

    @Temporal(TemporalType.DATE)
    private Date data;

    @Temporal(TemporalType.TIME)
    private Date hora;

    private char inclusoPlano;
    private Double custo;

    @ManyToOne
    @JoinColumn(name = "usuario_paciente_id", referencedColumnName = "usuarioPacienteId")
    private UsuarioPaciente usuarioPaciente;

    @ManyToOne
    @JoinColumn(name = "profissional_id", referencedColumnName = "profissionalId")
    private Profissional profissional;

    @ManyToOne
    @JoinColumn(name = "recomendacao_trat_id", referencedColumnName = "recomendacaoId")
    private RecomendacaoTrat recomendacaoTrat;
}
