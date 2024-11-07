package com.example.SmartoothAI.repository;

import com.example.SmartoothAI.model.RecomendacaoTratamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecomendacaoTratamentoRepository extends JpaRepository<RecomendacaoTratamento, Long> {
}

