package com.uce.edu.avanzada.budget_rent_a_car.controller;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Vehiculo;
import com.uce.edu.avanzada.budget_rent_a_car.service.IReservaService;
import com.uce.edu.avanzada.budget_rent_a_car.service.IVehiculoService;
import com.uce.edu.avanzada.budget_rent_a_car.service.to.ReservaClienteTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/reservas")
public class ReservaController {
    @Autowired
    private IReservaService iReservaService;

    @Autowired
    private IVehiculoService iVehiculoService;

    @PostMapping("/verDisponibilidad")
    public String verDisponibilidad(Model model, ReservaClienteTO reservaClienteTO) {
        String cedula = reservaClienteTO.getCedulaCliente();
        String placa = reservaClienteTO.getPlacaVehiculo();
        LocalDate fechaInicio = reservaClienteTO.getFechaInicioReserva();
        LocalDate fechaFin = reservaClienteTO.getFechaFinReserva();

        if (cedula.isEmpty() || placa.isEmpty() || fechaInicio == null || fechaFin == null) {
            if (reservaClienteTO.isAuxSinReserva()) // Si viene http://localhost:8085/reservas/mostrarRetirarSinReserva
                return "reservas/vistaRetirarSinReservaInicio";
            return "redirect:/clientes/inicioClientes";
        }


        boolean estaVehiculoDisponible = false;

        //Controlar fallo de placa y cedula---
        try {
            estaVehiculoDisponible = !this.iReservaService.verificar(fechaInicio, fechaFin, placa);
        } catch (Exception exception) {

            if (reservaClienteTO.isAuxSinReserva())  // Si viene http://localhost:8085/reservas/mostrarRetirarSinReserva
                return "reservas/vistaRetirarSinReservaInicio";
            return "clientes/vistaClientes";
        }// ---------

        if (!estaVehiculoDisponible) {
            // TODO Falta funcionalidad para esto en service
            StringBuilder fechasDondeEstaOcupado = new StringBuilder();
            List<String> intervalos = this.iReservaService.calcularIntervaloDias(fechaInicio, fechaFin, placa);
            for (String intervalo : intervalos) {
                fechasDondeEstaOcupado.append(intervalo);
            }

            model.addAttribute("fechasDondeEstaOcupado", fechasDondeEstaOcupado);
            return "reservas/vistaVehiculoNoDisponible";
        }

        Vehiculo vehiculo;

//        try {
        vehiculo = this.iVehiculoService.buscarPlaca(placa);
//        } catch (Exception e) {
//            return "reservas/vistaRetirarSinReservaInicio";
//        }

        if (vehiculo.getUrlImagen() == null)
            vehiculo.setUrlImagen(
                    "https://th.bing.com/th/id/OIG2.rKHSGXziRWnPAtOzQu86?w=1024&h=1024&rs=1&pid=ImgDetMain");

        List<BigDecimal> valoresPago = this.iReservaService.calcularValorTotal(fechaInicio, fechaFin, placa);
        BigDecimal valorTotal = valoresPago.get(2);
        reservaClienteTO.setTotalReserva(valorTotal);

        model.addAttribute("vehiculoDisponible", vehiculo);
        model.addAttribute("reservaClienteTO", reservaClienteTO);

        return "reservas/vistaPagarReserva";
    }

    @PostMapping("/pagar")
    public String reservar(Model model, ReservaClienteTO reservaClienteTO) {
        LocalDate fechaInicio = reservaClienteTO.getFechaInicioReserva();
        LocalDate fechaFin = reservaClienteTO.getFechaFinReserva();
        String placa = reservaClienteTO.getPlacaVehiculo();
        String cedula = reservaClienteTO.getCedulaCliente();
        String numTargeta = reservaClienteTO.getNumTargetaReserva();
        boolean auxVengoDesdeSinReserva = reservaClienteTO.isAuxSinReserva();

        if (numTargeta.isEmpty()) {
            return "";
        }

        String codigoReserva = this.iReservaService.reservar(fechaInicio, fechaFin, placa, cedula, numTargeta);
        model.addAttribute("codigoReserva", codigoReserva);

        if (auxVengoDesdeSinReserva)
            return "redirect:/reservas/retirarSinReserva/" + codigoReserva;
        return "reservas/vistaPagoExitoso";
    }

    // RETIRAR SIN RESERVA
    @GetMapping("/mostrarRetirarSinReserva")
    public String mostrarVistaRetirarSinReserva(Model model, ReservaClienteTO reservaClienteTO) {
        reservaClienteTO.setAuxSinReserva(true);

        model.addAttribute("reservaClienteTO", reservaClienteTO);

        try {
            List<Vehiculo> vehiculosDisponibles = this.iVehiculoService.buscarTodosSoloDisponibles();
            model.addAttribute("vehiculos", vehiculosDisponibles);
        } catch (Exception e) {
            model.addAttribute("vehiculos", Arrays.asList(new Vehiculo()));
        }

        return "reservas/vistaRetirarSinReservaInicio";
    }

    @GetMapping("/retirarSinReserva/{codigoReserva}")
    public String retirarSinReserva(@PathVariable(value = "codigoReserva") String codigoReserva) {
        try {
            this.iReservaService.retirar(codigoReserva);
        } catch (Exception e) {
            return "reservas/vistaRetirarSinReservaInicio";
        }

        return "reservas/vistaRetiradoSinReserva";
    }

}
