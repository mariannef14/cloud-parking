package com.dio.cloudparking.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Estacionamento {
    
    @Id
    private String id;
    private String licenca;
    private String estado;
    private String modelo;
    private String cor;
    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaida;
    private Double conta;


    public Estacionamento(){}

    public Estacionamento(String id, String licenca, String estado, String modelo, String cor){
        this.id = id;
        this.licenca = licenca;
        this.estado = estado;
        this.modelo = modelo;
        this.cor = cor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getLicenca() {
        return licenca;
    }

    public void setLicenca(String licenca) {
        this.licenca = licenca;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDateTime dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDateTime getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDateTime dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Double getConta(){
        return conta;
    }

    public void setConta(Double conta){
        this.conta = conta;
    }

}
