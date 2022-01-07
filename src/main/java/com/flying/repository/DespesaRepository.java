package com.flying.repository;

import com.flying.model.Despesa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DespesaRepository extends JpaRepository <Despesa, Long> {
}
