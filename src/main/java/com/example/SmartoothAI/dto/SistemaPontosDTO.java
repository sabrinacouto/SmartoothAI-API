package com.example.SmartoothAI.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Data
public class SistemaPontosDTO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sistemaPontosId;
    private Double totalPontos;
    private String tipoPontos;
    private Long planoId;
}

