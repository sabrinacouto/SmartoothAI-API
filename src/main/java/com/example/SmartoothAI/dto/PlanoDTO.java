package com.example.SmartoothAI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanoDTO {

    private Long planoId;
    private String descricao;
    private Date dataInicio;
    private Date dataFim;
    private Double valor;

}
