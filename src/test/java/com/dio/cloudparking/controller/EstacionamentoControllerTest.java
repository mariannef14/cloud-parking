package com.dio.cloudparking.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EstacionamentoControllerTest {
    
    @LocalServerPort
    private int randomPort;

    public void testeConfiguracao(){
        System.out.println(randomPort);
        RestAssured.port = randomPort;
    }

    @Test
    void buscarTodos(){}
}
