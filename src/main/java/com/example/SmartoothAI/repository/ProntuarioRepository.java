package com.example.SmartoothAI.repository;

import com.example.SmartoothAI.model.Prontuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {

}

