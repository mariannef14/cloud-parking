package com.dio.cloudparking.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.dio.cloudparking.dto.EstacionamentoCreateDTO;
import com.dio.cloudparking.dto.EstacionamentoDTO;
import com.dio.cloudparking.model.Estacionamento;

@Component
public class EstacionamentoMapper {
    
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public EstacionamentoDTO paraEstacionamentoDTO(Estacionamento estacionamento) {
        return MODEL_MAPPER.map(estacionamento, EstacionamentoDTO.class);
    }

    public List<EstacionamentoDTO> paraEstacionamentoDTOLista(List<Estacionamento> estacionamentoList) {
        return estacionamentoList.stream().map(this::paraEstacionamentoDTO).collect(Collectors.toList());
    }

    public Estacionamento paraEstacionamento(EstacionamentoCreateDTO estacionamentoDto) {
        return MODEL_MAPPER.map(estacionamentoDto, Estacionamento.class);
    }

    public Estacionamento paraCriarEstacionamento(EstacionamentoCreateDTO dto) {
        return MODEL_MAPPER.map(dto, Estacionamento.class);
    }
}
