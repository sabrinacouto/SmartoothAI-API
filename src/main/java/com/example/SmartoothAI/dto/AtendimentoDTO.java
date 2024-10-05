package com.example.SmartoothAI.dto;

import lombok.Data;
import java.util.Date;

@Data
public class AtendimentoDTO {

    private Long atendimentoId;
    private String descricao;
    private Date data;
    private Date hora;
    private char inclusoPlano;
    private Double custo;
    private Long usuarioPacienteId; // ID do usuário paciente
    private Long profissionalId;     // ID do profissional
    private Long recomendacaoTratId; // ID da recomendação de tratamento
}

