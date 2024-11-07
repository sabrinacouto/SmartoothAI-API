package com.example.SmartoothAI.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SistemaPontosDTO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sistemaPontosId;
    private Double totalPontos;
    private String tipoPontos;
    private Long planoId;
}

