package com.dio.cloudparking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

import com.dio.cloudparking.controller.mapper.EstacionamentoMapper;
import com.dio.cloudparking.dto.EstacionamentoCreateDTO;
import com.dio.cloudparking.dto.EstacionamentoDTO;
import com.dio.cloudparking.model.Estacionamento;
import com.dio.cloudparking.service.EstacionamentoService;

@RestController
@RequestMapping("/estacionamento")
public class EstacionamentoController {
    
    private final EstacionamentoMapper estacionamentoMapper;
    private final EstacionamentoService estacionamentoService;

    public EstacionamentoController(EstacionamentoMapper estacionamentoMapper, EstacionamentoService estacionamentoService){
        this.estacionamentoMapper = estacionamentoMapper;
        this.estacionamentoService = estacionamentoService;
    }


    @GetMapping("/")
    @Operation(summary = "busca todos os estacionamentos")
    public ResponseEntity<List<EstacionamentoDTO>> buscarTodos(){
        
        List<Estacionamento> estacionamentos = estacionamentoService.buscarTodos();

        return ResponseEntity.ok(estacionamentoMapper.paraEstacionamentoDTOLista(estacionamentos));
    }

    @GetMapping("/buscarEstacionamento/{id}")
    @Operation(summary = "busca um estacionamento pelo id")
    public ResponseEntity<EstacionamentoDTO> buscarPorId(@PathVariable String id){
        Estacionamento estacionamento = estacionamentoService.buscarPorId(id);

        return ResponseEntity.ok(estacionamentoMapper.paraEstacionamentoDTO(estacionamento));
    }

    @PostMapping("/add")
    @Operation(summary = "adiciona um estacionamento")
    public ResponseEntity<EstacionamentoDTO> criar(@RequestBody EstacionamentoCreateDTO dto){

        Estacionamento estacionamento = estacionamentoMapper.paraCriarEstacionamento(dto);
        estacionamento = estacionamentoService.criar(estacionamento); 

       return ResponseEntity.status(HttpStatus.CREATED).body(estacionamentoMapper.paraEstacionamentoDTO(estacionamento));
    }

    @PutMapping("/{id}")
    @Operation(summary = "atualiza um estacionamento pelo id")
    public ResponseEntity<EstacionamentoDTO> atualizar(@PathVariable String id, @RequestBody EstacionamentoCreateDTO estacionamentoDto) {

        Estacionamento novoEstacionamento = estacionamentoMapper.paraCriarEstacionamento(estacionamentoDto);
        Estacionamento estacionamento = estacionamentoService.atualizar(id, novoEstacionamento);

       return ResponseEntity.ok(estacionamentoMapper.paraEstacionamentoDTO(estacionamento));
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "deleta um estacionamento pelo id")
    public ResponseEntity deletar(@PathVariable String id) {

        estacionamentoService.deletar(id);
        
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/sair")
    @Operation(summary = "faz o checkout")
    public ResponseEntity<EstacionamentoDTO> sair(String id){

        Estacionamento estacionamento = estacionamentoService.verificacaoSaida(id);
        
        return ResponseEntity.ok(estacionamentoMapper.paraEstacionamentoDTO(estacionamento));
    }
}
