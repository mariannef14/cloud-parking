package com.dio.cloudparking.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dio.cloudparking.model.Estacionamento;

@RestController
@RequestMapping("estacionamento")
public class EstacionamentoController {
    
    @GetMapping
    public List<Estacionamento> findAll(){
        Estacionamento c1 = new Estacionamento("1", "DMS-1111", "PE", "HB20", "Prata");
        Estacionamento c2 = new Estacionamento("2", "AJN-1407", "SP", "Renegade", "Preto");

        List<Estacionamento> carros = new ArrayList<>();
        carros.add(c1);
        carros.add(c2);

        return carros;
    }
}
