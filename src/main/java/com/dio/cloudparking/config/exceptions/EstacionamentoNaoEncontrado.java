package com.dio.cloudparking.config.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EstacionamentoNaoEncontrado extends RuntimeException{
    
    public EstacionamentoNaoEncontrado(String id){
        super("Estacionamento que possui o id " + id + " não foi encontrado");
    }
}
