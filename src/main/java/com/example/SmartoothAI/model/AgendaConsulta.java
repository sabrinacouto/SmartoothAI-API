package com.example.SmartoothAI.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_agenda_consulta")
public class AgendaConsulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agenda_consulta_id")
    private Long agendaConsultaId;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_consulta")
    private Date dataConsulta;

    @Temporal(TemporalType.TIME)
    @Column(name = "hora_consulta")
    private Date horaConsulta;

    @ManyToOne
    @JoinColumn(name = "usuario_paciente_id", nullable = false)
    private UsuarioPaciente usuarioPaciente;

    @ManyToOne
    @JoinColumn(name = "profissional_id", nullable = false)
    private Profissional profissional;
}

