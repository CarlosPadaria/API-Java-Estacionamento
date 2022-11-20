package com.estacionamento.estacionamentoveiculos.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@Hidden
public class ErrorViewController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, HttpServletResponse response) {

        // TODO: log error details here


        System.out.println(response.getStatus());

        return response.getStatus()+"";
    }
}
