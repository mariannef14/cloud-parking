package com.dio.cloudparking.controller;

import com.dio.cloudparking.dto.EstacionamentoCreateDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EstacionamentoControllerTest {
    
    @LocalServerPort
    private int randomPort;

    private String hash = "dXNlcjp0ZXN0ZTEyMw==";
    
    @BeforeEach
    public void testeConfiguracao(){
        System.out.println(randomPort);
        RestAssured.port = randomPort;
    }

    @Test
    void quandoEncontrarTodosVerifiqueResultado(){

        RestAssured.given()   
        .headers("Authorization","Basic " + hash)
        .when()
        .get("/estacionamento/")
        .then()
        .statusCode(HttpStatus.OK.value());
        
    }

    @Test
    void quandoEncontrarEstacionamentoVerificaResultado(){

        RestAssured.given()
        .headers("Authorization","Basic " + hash)
        .when()
        .get("/estacionamento/buscarEstacionamento/{id}", "0cb3b694e76e4ceea916b28a8cdb0274")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("licenca", Matchers.equalTo("ABC-1605"))
        .body("modelo", Matchers.equalTo("GOL"))
        .body("cor", Matchers.equalTo("Vermelho"))
        .body("estado", Matchers.equalTo("RJ"));

    }

    
    @Test
    void quandoCriarEntaoVerifiqueECriado(){

        EstacionamentoCreateDTO estacionamento = new EstacionamentoCreateDTO();
        estacionamento.setLicenca("PTY-0745");
        estacionamento.setModelo("SANDERO");
        estacionamento.setCor("Preto");
        estacionamento.setEstado("RS");

        RestAssured.given()
        .headers("Authorization","Basic " + hash)
        .when()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(estacionamento)
        .post("/estacionamento/add")
        .then()
        .statusCode(HttpStatus.CREATED.value())
        .body("licenca", Matchers.equalTo("PTY-0745"))
        .body("modelo", Matchers.equalTo("SANDERO"))
        .body("cor", Matchers.equalTo("Preto"))
        .body("estado", Matchers.equalTo("RS"));

    }

    @Test
    void quandoAtualizarVerificarAtualizacao(){

        EstacionamentoCreateDTO novoEstacionamento = new EstacionamentoCreateDTO();
        novoEstacionamento.setLicenca("PWL-5002");
        novoEstacionamento.setModelo("Palio");
        novoEstacionamento.setCor("Branco");
        novoEstacionamento.setEstado("PB");

        RestAssured.given()
        .headers("Authorization","Basic " + hash)
        .when()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(novoEstacionamento)
        .put("/estacionamento/{id}", "6519c1d9648342c88c4f6c7d44ea2448")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("licenca", Matchers.equalTo("PWL-5002"))
        .body("modelo", Matchers.equalTo("Palio"))
        .body("cor", Matchers.equalTo("Branco"))
        .body("estado", Matchers.equalTo("PB"));
        
    }

    
    @Test
    void quandoDeletarVerificarResultado(){
        RestAssured.given()
        .headers("Authorization","Basic " + hash)
        .when()
        .delete("/estacionamento/deletar/{id}", "bb1af0f0ffae4fba97a03ccce203368f")
        .then()
        .statusCode(HttpStatus.NO_CONTENT.value());
        
    }
}
   
   
    
