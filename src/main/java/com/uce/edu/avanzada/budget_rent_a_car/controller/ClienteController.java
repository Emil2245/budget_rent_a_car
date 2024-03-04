package com.uce.edu.avanzada.budget_rent_a_car.controller;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Cliente;
import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Vehiculo;
import com.uce.edu.avanzada.budget_rent_a_car.service.to.ReservaClienteTO;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/budget/clientes")
public class ClienteController {

    @GetMapping("/inicioClientes")
    public String mostrarInicio(Model model, ReservaClienteTO reservaClienteTO) {
        model.addAttribute("reservaClienteTO", new ReservaClienteTO());

        List<Vehiculo> vehiculosFiltrados = this.getVehiculosMock();

        String marca = reservaClienteTO.getMarcaVehiculo();
        String modelo = reservaClienteTO.getModeloVehiculo();

        if (marca == null && modelo == null) {
            model.addAttribute("vehiculos", Arrays.asList(new Vehiculo()));
        } else {
            model.addAttribute("vehiculos", vehiculosFiltrados);
        }

        return "vistaClientes";
    }

    public List<Vehiculo> getVehiculosMock() {
        Vehiculo v1 = new Vehiculo();
        v1.setPlaca("PCB-2480");
        v1.setModelo("Sedán");
        v1.setMarca("Toyota");
        v1.setEstado("Indisponible");
        v1.setAnioFabricacion("2002");
        v1.setCilindraje("2000 cc");
        v1.setPaisFabricacion("Japón");
        v1.setAvaluo("5000 USD");
        v1.setValorDia(new BigDecimal("3.5"));
        Vehiculo v2 = new Vehiculo();
        v2.setPlaca("PCB-2480");
        v2.setModelo("Hatchback");
        v2.setMarca("Volkswagen");
        v2.setEstado("Disponible");
        v2.setAnioFabricacion("2021");
        v2.setCilindraje("1500 cc");
        v2.setPaisFabricacion("Alemania");
        v2.setAvaluo("12000 USD");
        v2.setValorDia(new BigDecimal("4.2"));


        return Arrays.asList(v1, v2);
    }

    @GetMapping("/nuevoCliente")
    public String registrarNuevo(Model model) {
        Cliente cliente = new Cliente();
        cliente.setRegistro("c"); // c -> cliente
        model.addAttribute("cliente", cliente);
        return "vistaNuevoCliente";
    }

    @GetMapping("/nuevoClienteDesdeEmpleado")
    public String registrarNuevoDesdeEmpleado(Model model) {
        Cliente cliente = new Cliente();
        cliente.setRegistro("e"); // e -> empleado
        model.addAttribute("cliente", cliente);
        return "vistaNuevoCliente";
    }

    @PostMapping("/registrar")
    public String registrar(Cliente cliente) {
        // TODO guardar cliente
        return "redirect:/budget/clientes/inicioClientes";
    }

    @PostMapping("/buscarVehículos")
    public String buscarVehiculos(Cliente cliente) {
        // TODO guardar cliente
        return "redirect:/budget/clientes/inicioClientes";
    }


}
