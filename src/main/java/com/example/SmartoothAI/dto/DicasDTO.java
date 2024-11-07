package com.example.SmartoothAI.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DicasDTO {

    private Long dicaId;

    @NotBlank(message = "A descrição é obrigatória.")
    private String descricao;

    @NotNull(message = "O prontuário é obrigatório.")
    private Long prontuarioId;

    @NotNull(message = "O ID do usuário paciente é obrigatório.")
    private Long usuarioPacienteId;
}