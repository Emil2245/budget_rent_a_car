package com.uce.edu.avanzada.budget_rent_a_car.controller;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Cliente;
import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Reserva;
import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Vehiculo;
import com.uce.edu.avanzada.budget_rent_a_car.repository.model.dto.ReservaDTO;
import com.uce.edu.avanzada.budget_rent_a_car.service.IClienteService;
import com.uce.edu.avanzada.budget_rent_a_car.service.IEmpleadoService;
import com.uce.edu.avanzada.budget_rent_a_car.service.IReservaService;
import com.uce.edu.avanzada.budget_rent_a_car.service.IVehiculoService;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private IVehiculoService iVehiculoService;

    @Autowired
    private IClienteService iClienteService;

    @Autowired
    private IReservaService iReservaService;

    private static final Logger LOG = Logger.getLogger(EmpleadoController.class);

    @Autowired
    private IEmpleadoService iEmpleadoService;

    // http://localhost:8085/empleados/inicio
    @GetMapping("/inicio")
    public String inicioEmpleados() {

        return "vistaPrincipalEmpleados";
    }

    @GetMapping("/regresar")
    public String regresar() {
        return "redirect:/empleados/inicio";
    }

    // Funcionalidad 2a
    @GetMapping("/lista_clientes")
    public String buscarClientes(Model modelo) {
        List<Cliente> listaClientes = this.iClienteService.verClientes();
        modelo.addAttribute("clientes", listaClientes);

        return "vistaListaClientesEmpleado";
    }

    @PostMapping("/guardar")
    public String registrar(Cliente cliente) {
        this.iClienteService.crear(cliente);

        return "redirect:/empleados/lista_clientes";
    }

    @GetMapping("/nuevoCliente")
    public String nuevoCliente(Cliente cliente) {
        return "vistaNuevoClienteEmpleado";

    }

    @GetMapping("/buscar_cliente")
    public String buscarCliente(Cliente cliente) {
        return "vistaClienteBuscar";
    }

    @GetMapping("/cliente_cedula")
    public String cedulaCliente(Cliente cliente, Model modelo) {
        List<Cliente> lista = new ArrayList<>();
        try {

            lista.add(this.iClienteService.buscarPorCedula(cliente.getCedula()));
        } catch (Exception e) {
            return "vistaCedulaCliente";

        }

        modelo.addAttribute("lista", lista);
        return "vistaCedulaCliente";
    }

    @GetMapping("/nuevo_vehiculo")
    public String nuevoVehiculo(Vehiculo vehiculo) {
        return "vistaNuevoVehiculo";

    }

    @PostMapping("/guardarVehiculo")
    public String registrarVehiculo(Vehiculo vehiculo) {
        this.iVehiculoService.guardar(vehiculo);

        return "redirect:/empleados/lista_vehiculos";
    }

    @GetMapping("/lista_vehiculos")
    public String listaVehiculos(Model modelo) {
        List<Vehiculo> listaVehiculos = this.iVehiculoService.buscarTodos();
        modelo.addAttribute("vehiculos", listaVehiculos);

        return "vistaListaVehiculos";
    }

    // http://localhost:8080/rentaCar/empleados/vehiculos_placa
    @GetMapping("/vehiculos_placa")
    public String vehiculosP(Vehiculo vehiculo, Model modelo) {
        List<Vehiculo> lista = new ArrayList<>();
        lista.add(this.iVehiculoService.buscarPlaca(vehiculo.getPlaca()));
        modelo.addAttribute("lista", lista);

        return "vistaVehiculosPlaca";
    }

    // http://localhost:8080/rentaCar/empleados/buscar_placa
    @GetMapping("/buscar_placa")
    public String buscarP(Vehiculo vehiculo) {
        return "vistaVehiculoBuscarPlaca";
    }

    @GetMapping("/retirar_vehiculo")
    public String retirarSinReserva(Model modelo) {
        List<Vehiculo> lista = this.iVehiculoService.buscarTodos();
        modelo.addAttribute("lista", lista);
        return "vistaRetirarSinReservar";
    }

    // http://localhost:8080/rentaCar/empleados/reservar
    @GetMapping("/reservar")
    public String reservar(ReservaDTO reservaDTO) {
        return "vistaReserva";
    }

    @GetMapping("/confirmacion_reserva")
    public String confirmacionReserva(ReservaDTO reservaDTO, Model model, HttpSession session) {
        if (this.iReservaService.verificar(reservaDTO.getFechaInicio(), reservaDTO.getFechaFin(),
                reservaDTO.getPlaca())) {
            LOG.warn("Error al confirmar su reserva");
            return "vistaError";
        } else {
            var total = this.iReservaService
                    .calcularValorTotal(reservaDTO.getFechaInicio(), reservaDTO.getFechaFin(), reservaDTO.getPlaca())
                    .get(2);
            model.addAttribute("total", total);
            session.setAttribute("reserva", reservaDTO);

            return "vistaConfirmacionReserva";
        }

    }

    @PostMapping("/reservado")
    public String reservado(HttpSession session, ReservaDTO reserva, Model model) {
        ReservaDTO reservaDTO = (ReservaDTO) session.getAttribute("reserva");
        String codigo = this.iReservaService.reservar(reservaDTO.getFechaInicio(), reservaDTO.getFechaFin(),
                reservaDTO.getPlaca(), reservaDTO.getCedula(), reserva.getNumeroTarjeta());
        model.addAttribute("codigo", codigo);
        return "vistaRetirarReserva";
    }

    @GetMapping("/retirar_reservado")
    public String ingresarCodigo(Reserva reserva) {
        return "vistaRetirarReserva";
    }

    @GetMapping("/retirar")
    public String retirar(Reserva reserva, Model model, HttpSession session) {

        String str;
        try {
            str = this.iReservaService.getReserva(reserva.getCodigo());
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            return "redirect:/empleados/retirar_reservado";
        }

        session.setAttribute("codigo", reserva.getCodigo());
        model.addAttribute("reserva", str);
        return "vistaRetirar";
    }

    @GetMapping("/aplicar_retiro")
    public String aplicar(Model model, HttpSession session) {
        String codigo = (String) session.getAttribute("codigo");
        System.out.println("Codigo " + codigo);
        this.iReservaService.aplicar(codigo);
        return "vistaNotificacionReserva";
    }

}
