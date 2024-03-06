package com.uce.edu.avanzada.budget_rent_a_car.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class InicioController {
    @GetMapping("/")
    public String vistaInicioPrograma() {
        return "vistaInicio";
    }
}
