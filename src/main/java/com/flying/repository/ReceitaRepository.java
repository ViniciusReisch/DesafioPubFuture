package com.flying.repository;

import com.flying.model.Receita;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceitaRepository extends JpaRepository <Receita, Long> {}
