package com.uce.edu.avanzada.budget_rent_a_car.controller;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Vehiculo;
import com.uce.edu.avanzada.budget_rent_a_car.service.IReservaService;
import com.uce.edu.avanzada.budget_rent_a_car.service.IVehiculoService;
import com.uce.edu.avanzada.budget_rent_a_car.service.to.ReservaClienteTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/budget/reservas")
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

        if (cedula.isEmpty() || placa.isEmpty() || fechaInicio == null || fechaFin == null)
            return "redirect:/budget/clientes/inicioClientes";


        // TODO Pregunta que hace verificar
        boolean estaVehiculoDisponible = !this.iReservaService.verificar(fechaInicio, fechaFin, placa);

        if (!estaVehiculoDisponible) {
            String fechaDondeEstaraDisponible = "01/01/test"; //TODO Falra funcionalidad para esto en service
            model.addAttribute("fechaDondeEstaraDisponible", fechaDondeEstaraDisponible);
            return "reservas/vistaVehiculoNoDisponible";
        }

        Vehiculo vehiculo = this.iVehiculoService.buscarPlaca(placa);
        if (vehiculo.getUrlImagen() == null)
            vehiculo.setUrlImagen("https://th.bing.com/th/id/OIG2.rKHSGXziRWnPAtOzQu86?w=1024&h=1024&rs=1&pid=ImgDetMain");

        List<BigDecimal> valoresPago = this.iReservaService.calcularValorTotal(fechaInicio, fechaFin, placa);
        BigDecimal valorTotal = valoresPago.get(2);
        reservaClienteTO.setTotalReserva(valorTotal);

        model.addAttribute("vehiculoDisponible", vehiculo);
        model.addAttribute("reservaClienteTO", reservaClienteTO);
        return "reservas/vistaPagarReserva";
    }

    @PostMapping("/pagar")
    public String reservar(Model model, ReservaClienteTO reservaClienteTO) {
        // TODO logica para registrar reserva,
        // String codigoReserva = this.reservar(LocalDate inicio, LocalDate fin, String placa, String cedula, String tarjeta);
        LocalDate fechaInicio = reservaClienteTO.getFechaInicioReserva();
        LocalDate fechaFin = reservaClienteTO.getFechaFinReserva();
        String placa = reservaClienteTO.getPlacaVehiculo();
        String cedula = reservaClienteTO.getCedulaCliente();
        String numTargeta = reservaClienteTO.getNumTargetaReserva();

        if (numTargeta.isEmpty()) {
            // TODO LOGICA SI EL INPUT DE TARJETA NO ES VALIDO
            return "";
        }


        String codigoReserva = this.iReservaService.reservar(fechaInicio, fechaFin, placa, cedula, numTargeta);
        model.addAttribute("codigoReserva", codigoReserva);
        return "reservas/vistaPagoExitoso";
    }


}
