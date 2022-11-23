package com.dio.cloudparking.repository;

import com.dio.cloudparking.model.Estacionamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstacionamentoRepository extends JpaRepository<Estacionamento, String>{
    
}