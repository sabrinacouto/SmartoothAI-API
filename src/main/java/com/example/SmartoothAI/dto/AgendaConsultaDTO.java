package com.example.SmartoothAI.dto;

import lombok.Data;
import java.util.Date;

@Data
public class AgendaConsultaDTO {

    private Long agendaConsultaId;
    private Date dataConsulta;
    private Date horaConsulta;
    private Long usuarioPacienteId;
    private Long profissionalId;
}

