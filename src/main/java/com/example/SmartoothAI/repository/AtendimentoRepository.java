package com.example.SmartoothAI.repository;

import com.example.SmartoothAI.model.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
}

