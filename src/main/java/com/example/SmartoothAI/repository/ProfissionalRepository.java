package com.example.SmartoothAI.repository;


import com.example.SmartoothAI.model.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
    boolean existsByEmail(String email);
    Optional<Profissional> findByEmail(String email);
}


