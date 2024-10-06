package com.example.SmartoothAI.repository;

import com.example.SmartoothAI.model.RecomendacaoTrat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecomendacaoTratRepository extends JpaRepository<RecomendacaoTrat, Long> {
}

