package com.example.SmartoothAI.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class ProntuarioDTO {

    @NotBlank(message = "A prescrição não pode estar vazia.")
    @Size(max = 255, message = "A prescrição deve ter no máximo 255 caracteres.")
    private String prescricao;

    @Size(max = 255, message = "As observações devem ter no máximo 255 caracteres.")
    private String observacoes;

    private Date dataRegistro;
}

