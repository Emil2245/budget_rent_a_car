package com.uce.edu.avanzada.budget_rent_a_car.controller;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Cliente;
import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Vehiculo;
import com.uce.edu.avanzada.budget_rent_a_car.service.to.ReservaClienteTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/budget/reservas")
public class ReservaController {

    @PostMapping("/verDisponibilidad")
    public String verDisponibilidad(Model model, ReservaClienteTO reservaClienteTO) {

        // TODO llamar a un método desde service que devuelva el vehiculo DISPONIBLE enlas fechas dadas
        //  Vehiculo v = this...verDisponibilidad(placa, fechaInicio, fechaFin);
        //  SELECT v FROM Vehiculo v WHERE placa = :placa AND estado = disponible AND  fechaIninicio = ... AND fechaFin= ...
        // Si no el Vehiculo no esta disponible en las fechas dadas, devolver null;


        // CUANDO NOOO ESTA DISPONIBLE (NULL)------
        // Vehiculo vehiculoDisponibleEnFechas = null; // Aqui seria la llamada al metodo usando los valores de reservaClienteTO
        //----------------------------------------


        // CUANDO SI ESTA DISPONIBLE--------------
        Vehiculo vehiculoDisponibleEnFechas = new Vehiculo(); // TODO Se debe recuperar desde el service
        vehiculoDisponibleEnFechas.setPlaca("PLC-324");
        vehiculoDisponibleEnFechas.setModelo("Picksy");
        vehiculoDisponibleEnFechas.setMarca("Toyota");
        vehiculoDisponibleEnFechas.setAvaluo("23000 USD");
        /// ...mas atributos
        //----------------------------------------


        if (vehiculoDisponibleEnFechas == null) {
            model.addAttribute("mensaje", "El vehículo no está disponible en las fechas seleccionadas");
            return "vistaVehiculoNoDisponible";
        }
        model.addAttribute("vehiculoDisponible", vehiculoDisponibleEnFechas);
        return "vistaPagarReserva";
    }

}
