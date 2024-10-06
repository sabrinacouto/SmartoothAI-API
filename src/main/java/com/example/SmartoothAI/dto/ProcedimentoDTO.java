package com.example.SmartoothAI.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProcedimentoDTO {

    @NotBlank(message = "O nome do procedimento não pode estar em branco.")
    @Size(max = 85, message = "O nome do procedimento não pode ter mais de 85 caracteres.")
    private String nomeProcedimento;

    @Size(max = 255, message = "A descrição não pode ter mais de 255 caracteres.")
    private String descricao;

    @NotNull(message = "A inclusão no plano não pode ser nula.")
    private Character inclusaoPlano;

    @NotNull(message = "O ID do prontuário não pode ser nulo.")
    private Long prontuarioId;

    @NotNull(message = "O ID do sistema de pontos não pode ser nulo.")
    private Long sistemaPontosId;

    @NotNull(message = "O ID do sistema de pontos do plano não pode ser nulo.")
    private Long sistPontosPlanoId;

    @NotNull(message = "O ID do usuário paciente não pode ser nulo.")
    private Long usPacienteId;
}

