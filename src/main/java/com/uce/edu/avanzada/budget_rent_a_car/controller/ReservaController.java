package com.uce.edu.avanzada.budget_rent_a_car.controller;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Vehiculo;
import com.uce.edu.avanzada.budget_rent_a_car.service.to.ReservaClienteTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@Controller
@RequestMapping("/budget/reservas")
public class ReservaController {

    @PostMapping("/verDisponibilidad")
    public String verDisponibilidad(Model model, ReservaClienteTO reservaClienteTO) {

        // TODO llamar a un método desde service que devuelva el vehiculo DISPONIBLE enlas fechas dadas
        //  Vehiculo v = this...verDisponibilidad(placa, fechaInicio, fechaFin);
        //  SELECT v FROM Vehiculo v WHERE placa = :placa AND estado = disponible AND  fechaIninicio = ... AND fechaFin= ...
        // Si no el Vehiculo no esta disponible en las fechas dadas, devolver null;


//        //CUANDO NOOO ESTA DISPONIBLE (NULL)------
//        Vehiculo vehiculoDisponibleEnFechas = null; // Aqui seria la llamada al metodo usando los valores de reservaClienteTO
//        //----------------------------------------


        // CUANDO SI ESTA DISPONIBLE--------------
        Vehiculo vehiculoDisponibleEnFechas = new Vehiculo(); // TODO Se debe recuperar desde el service
        vehiculoDisponibleEnFechas.setPlaca("PLC-324");
        vehiculoDisponibleEnFechas.setModelo("Picksy");
        vehiculoDisponibleEnFechas.setMarca("Toyota");
        vehiculoDisponibleEnFechas.setModelo("Prius");
        vehiculoDisponibleEnFechas.setAvaluo("23000 USD");
        vehiculoDisponibleEnFechas.setCilindraje("200 cc");
        vehiculoDisponibleEnFechas.setEstado("Disponible");
        vehiculoDisponibleEnFechas.setPaisFabricacion("Japón");
        vehiculoDisponibleEnFechas.setAnioFabricacion("2019");
        vehiculoDisponibleEnFechas.setValorDia(new BigDecimal(40));

        vehiculoDisponibleEnFechas.setUrlImagen("https://imgs.search.brave.com/ReZTJLSxCtmkSBoDHzE5Iz52An9yMD9dlhg3hDhhudg/rs:fit:860:0:0/g:ce/aHR0cHM6Ly9tZWRp/YS5nZXR0eWltYWdl/cy5jb20vaWQvNDU5/NDQ1ODUxL3Bob3Rv/L3RveW90YS1wcml1/cy5qcGc_cz02MTJ4/NjEyJnc9MCZrPTIw/JmM9OGRDdF9lSGxP/YzhMcUxEQllYME42/N0FpZFNNd2lRT0ZT/LVhzMUxYcnBjQT0");

        //Imagen por defecto
        if (vehiculoDisponibleEnFechas.getUrlImagen() == null) {
            vehiculoDisponibleEnFechas.setUrlImagen("https://th.bing.com/th/id/OIG2.rKHSGXziRWnPAtOzQu86?w=1024&h=1024&rs=1&pid=ImgDetMain");
        }
        /// ...mas atributos
        //----------------------------------------


        if (vehiculoDisponibleEnFechas == null) {
            String fechaDondeEstaraDisponible = "01/01/test"; //TODO Falra funcionalidad para esto en service
            model.addAttribute("fechaDondeEstaraDisponible", fechaDondeEstaraDisponible);
            return "reservas/vistaVehiculoNoDisponible";
        }
        model.addAttribute("vehiculoDisponible", vehiculoDisponibleEnFechas);
        model.addAttribute("reservaClienteTO", reservaClienteTO);
        return "reservas/vistaPagarReserva";
    }

    @PostMapping("/pagar")
    public String reservar(Model model, ReservaClienteTO reservaClienteTO) {
        // TODO logica para registrar reserva,
        // String codigoReserva = this.reservar(LocalDate inicio, LocalDate fin, String placa, String cedula, String tarjeta);
        String codigoReserva = "R-test";
        model.addAttribute("codigoReserva", codigoReserva);
        return "reservas/vistaPagoExitoso";
    }


}
