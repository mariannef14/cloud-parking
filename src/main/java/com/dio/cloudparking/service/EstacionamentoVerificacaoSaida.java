package com.dio.cloudparking.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import com.dio.cloudparking.model.Estacionamento;

public class EstacionamentoVerificacaoSaida {

    public static final int UMA_HORA = 60;
    public static final int VINTE_QUATRO_HORAS = 24 * UMA_HORA;
    public static final double VALOR_UMA_HORA = 5.00;
    public static final double VALOR_ADICIONAL_POR_HORA = 2.00;
    public static final double VALOR_DO_DIA = 20.00;

    private static Double getConta(LocalDateTime dataEntrada, LocalDateTime dataSaida) {
        long minutos = dataEntrada.until(dataSaida, ChronoUnit.MINUTES);
        Double conta = 0.0;

        if (minutos <= UMA_HORA) {
            return VALOR_UMA_HORA;
        }

        if (minutos <= VINTE_QUATRO_HORAS) {
            conta = VALOR_UMA_HORA;
            int horas = (int) (minutos / UMA_HORA);
            System.out.println(horas);
            for (int i = 0; i < horas; i++) {
                conta += VALOR_ADICIONAL_POR_HORA;
            }

            return conta;
        }

        int dias = (int) (minutos / VINTE_QUATRO_HORAS);
        System.out.println(dias);
        for (int i = 0; i < dias; i++) {
            conta += VALOR_DO_DIA;
        }
        
        return conta;
    }

    public static Double getConta(Estacionamento estacionamento) {
        return getConta(estacionamento.getDataEntrada(), estacionamento.getDataSaida());
    }
}