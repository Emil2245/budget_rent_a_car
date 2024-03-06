package com.uce.edu.avanzada.budget_rent_a_car.controller;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Cliente;
import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Vehiculo;
import com.uce.edu.avanzada.budget_rent_a_car.service.IClienteService;
import com.uce.edu.avanzada.budget_rent_a_car.service.IVehiculoService;
import com.uce.edu.avanzada.budget_rent_a_car.service.to.ReservaClienteTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private IClienteService iClienteService;
    @Autowired
    private IVehiculoService iVehiculoService;

    // http://localhost:8080/clientes/inicioClientes
    @GetMapping("/inicioClientes")
    public String mostrarInicio(Model model, ReservaClienteTO reservaClienteTO) {
        model.addAttribute("reservaClienteTO", new ReservaClienteTO());

        String marca = reservaClienteTO.getMarcaVehiculo();
        String modelo = reservaClienteTO.getModeloVehiculo();

        try {
            List<Vehiculo> vehiculosFiltrados = this.iVehiculoService.buscarVehiculosPorMarcaYModelo(marca, modelo);
            model.addAttribute("vehiculos", vehiculosFiltrados);
        } catch (Exception e) {
            model.addAttribute("vehiculos", Arrays.asList(new Vehiculo()));
        }

        return "clientes/vistaClientes";
    }


    @GetMapping("/nuevoCliente")
    public String registrarNuevoDesdeEmpleado(Model model) {
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);
        return "clientes/vistaNuevoCliente";
    }

    @GetMapping("/nuevoClienteDesdeEmpleado")
    public String registrarNuevo(Model model) {
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);
        return "clientes/vistaNuevoClienteDesdeEmpleado";
    }


    @PostMapping("/registrarDesdeCliente")
    public String registrarDesdeCliente(Cliente cliente) {
        cliente.setRegistro("C"); // C -> Cliente
        this.iClienteService.guardar(cliente);

        return "redirect:/clientes/inicioClientes";
    }

    //http://localhost:8085/clientes/registrarDesdeEmpleado
    @PostMapping("/registrarDesdeEmpleado")
    public String registrarDesdeEmpleado(Cliente cliente) {
        cliente.setRegistro("E"); // E -> Empleado
        this.iClienteService.guardar(cliente);

        return "redirect:/empleados/inicio";
    }

    @PostMapping("/buscarVehículos")
    public String buscarVehiculos(Cliente cliente) {
        return "redirect:/clientes/inicioClientes";
    }

}
