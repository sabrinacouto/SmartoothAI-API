package com.example.SmartoothAI.model;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_procedimento")
public class Procedimento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
            "procedimento_seq")
    @SequenceGenerator(name = "procedimento_seq", sequenceName =
            "seq_procedimento_id", allocationSize = 1)
    @Column(name = "procedimento_id")
    private Long procedimentoId;
    @Column(name = "nome_procedimento", length = 85, nullable = false)
    private String nomeProcedimento;
    @Column(name = "descricao", length = 255)
    private String descricao;
    @Column(name = "inclusao_plano", length = 1)
    private Character inclusaoPlano;
    @Column(name = "prontuario_id", nullable = false)
    private Long prontuarioId;
    @Column(name = "sistema_pontos_id", nullable = false)
    private Long sistemaPontosId;
    @Column(name = "sist_pontos_plano_id", nullable = false)
    private Long sistPontosPlanoId;
    @Column(name = "us_paciente_id", nullable = false)
    private Long usPacienteId;
}

