package com.example.SmartoothAI.repository;

import com.example.SmartoothAI.model.SistemaPontos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SistemaPontosRepository extends JpaRepository<SistemaPontos, Long> {

}

