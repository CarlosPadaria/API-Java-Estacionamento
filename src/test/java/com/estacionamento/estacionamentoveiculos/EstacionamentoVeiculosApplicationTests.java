package com.estacionamento.estacionamentoveiculos;

import com.estacionamento.estacionamentoveiculos.model.Veiculo;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;


@SpringBootTest
class EstacionamentoVeiculosApplicationTests {

    @Test
    void buscarTodosOsVeiculos() {



        String token = String.valueOf(given().when().auth().basic("user", "123").
                contentType(MediaType.APPLICATION_JSON_VALUE).
                body("").contentType(ContentType.JSON)
                .get("/veiculos")
                .then()
                .extract().statusCode());

        Assertions.assertEquals("200", token);
    }


   /* @Test
    void validarCalculadorDeConta(){

    }*/

}
