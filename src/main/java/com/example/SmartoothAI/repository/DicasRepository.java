package com.example.SmartoothAI.repository;

import com.example.SmartoothAI.model.Dicas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DicasRepository extends JpaRepository<Dicas, Long> {
}
