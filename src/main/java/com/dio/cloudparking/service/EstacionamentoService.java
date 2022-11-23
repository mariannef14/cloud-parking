package com.dio.cloudparking.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import com.dio.cloudparking.config.exceptions.EstacionamentoNaoEncontrado;
import com.dio.cloudparking.model.Estacionamento;
import com.dio.cloudparking.repository.EstacionamentoRepository;

import org.springframework.stereotype.Service;

@Service
public class EstacionamentoService {
    
    private final EstacionamentoRepository estacionamentoRepository;

    public EstacionamentoService(EstacionamentoRepository estacionamentoRepository){
        this.estacionamentoRepository = estacionamentoRepository;
    }

    @Transactional(readOnly = true)
    public List<Estacionamento> buscarTodos(){
       
        return estacionamentoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Estacionamento buscarPorId(String id){

        Optional<Estacionamento> estacionamento = estacionamentoRepository.findById(id);

        if(estacionamento.isPresent())
            return estacionamento.get();
        else
            throw new EstacionamentoNaoEncontrado(id);
    }

    @Transactional
    public Estacionamento criar(Estacionamento estacionamento){

        estacionamento.setId(getUUID());
        estacionamento.setDataEntrada(LocalDateTime.now());

        estacionamentoRepository.save(estacionamento);

        return estacionamento;
    }

    @Transactional
    public Estacionamento atualizar(String id, Estacionamento estacionamentoCriado){

        Estacionamento estacionamento = buscarPorId(id);

        estacionamento.setLicenca(estacionamentoCriado.getLicenca());
        estacionamento.setEstado(estacionamentoCriado.getEstado());
        estacionamento.setCor(estacionamentoCriado.getCor());
        estacionamento.setModelo(estacionamentoCriado.getModelo());
        estacionamento.setDataEntrada(LocalDateTime.now());

        estacionamentoRepository.save(estacionamento);

        return estacionamento;
    }

    @Transactional
    public void deletar(String id){

        buscarPorId(id);

        estacionamentoRepository.deleteById(id);
    }

    @Transactional
    public Estacionamento verificacaoSaida(String id){

        Estacionamento estacionamento = buscarPorId(id);

        estacionamento.setDataSaida(LocalDateTime.now());
        estacionamento.setConta(EstacionamentoVerificacaoSaida.getConta(estacionamento));
    
        return estacionamentoRepository.save(estacionamento);
    }

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

}
