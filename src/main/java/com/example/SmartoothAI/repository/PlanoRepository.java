package com.example.SmartoothAI.repository;

import com.example.SmartoothAI.model.Plano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanoRepository extends JpaRepository<Plano, Long> {

    List<Plano> findByUsuarioPaciente_PacienteId(Long pacienteId);;
}

