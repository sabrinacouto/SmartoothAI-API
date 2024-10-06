package com.example.SmartoothAI.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.Date;

@Data
public class PlanoDTO {

    private Long planoId;

    @NotBlank(message = "A descrição é obrigatória.")
    private String descricao;

    @NotNull(message = "A data de início é obrigatória.")
    private Date dataInicio;

    @NotNull(message = "A data de fim é obrigatória.")
    private Date dataFim;

    @Positive(message = "O valor deve ser positivo.")
    private Double valor;
}

