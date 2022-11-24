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

    @BeforeEach
    public void testeConfiguracao(){
        System.out.println(randomPort);
        RestAssured.port = randomPort;
    }

    @Test
    void quandoEncontrarTodosVerifiqueResultado(){
        
        RestAssured.given()
        .auth().basic("user", "teste123")
        .when()
        .get("/estacionamento/")
        .then()
        .statusCode(HttpStatus.OK.value());

    }

    @Test
    void quandoEncontrarEstacionamentoVerificaResultado(){

        RestAssured.given()
        .auth().basic("user", "teste123")
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
        estacionamento.setLicenca("KTB-1907");
        estacionamento.setModelo("VOYAGE");
        estacionamento.setCor("Preto");
        estacionamento.setEstado("SP");

        RestAssured.given()
        .auth().basic("user", "teste123")
        .when()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(estacionamento)
        .post("/estacionamento/add")
        .then()
        .statusCode(HttpStatus.CREATED.value())
        .body("licenca", Matchers.equalTo("KTB-1907"))
        .body("modelo", Matchers.equalTo("VOYAGE"))
        .body("cor", Matchers.equalTo("Preto"))
        .body("estado", Matchers.equalTo("SP"));

    }

    @Test
    void quandoAtualizarVerificarAtualizacao(){

        EstacionamentoCreateDTO novoEstacionamento = new EstacionamentoCreateDTO();
        novoEstacionamento.setLicenca("PWL-5001");
        novoEstacionamento.setModelo("Palio");
        novoEstacionamento.setCor("Branco");
        novoEstacionamento.setEstado("PB");

        RestAssured.given()
        .auth().basic("user", "teste123")
        .when()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(novoEstacionamento)
        .put("/estacionamento/{id}", "6519c1d9648342c88c4f6c7d44ea2448")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("licenca", Matchers.equalTo("PWL-5001"))
        .body("modelo", Matchers.equalTo("Palio"))
        .body("cor", Matchers.equalTo("Branco"))
        .body("estado", Matchers.equalTo("PB"));
        
    }

    
    @Test
    void quandoDeletarVerificarResultado(){
        RestAssured.given()
        .auth().basic("user", "teste123")
        .when()
        .delete("/estacionamento/deletar/{id}", "6135427b8ced4ae8b7bdf3ff8f1ccd5c")
        .then()
        .statusCode(HttpStatus.NO_CONTENT.value());
    }
}
   
   
    
