package com.uce.edu.avanzada.budget_rent_a_car.controller;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Cliente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@Controller
@RequestMapping("/budget/clientes")
public class ClienteController {

    private static final Logger LOG = Logger.getLogger(ClienteController.class.getName());

    @GetMapping("/inicio")
    public String mostrarInicio(Model model) {
        Cliente cliente = new Cliente();
        cliente.setRegistro("c"); // c -> cliente
        model.addAttribute("cliente", cliente);
        return "vistaClientes";
    }
    @GetMapping("/nuevoRegistro")
    public String registrarNuevo(Model model) {
        Cliente cliente = new Cliente();
        cliente.setRegistro("c"); // c -> cliente
        model.addAttribute("cliente", cliente);
        return "vistaNuevoRegistro";
    }


}
