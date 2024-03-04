package com.uce.edu.avanzada.budget_rent_a_car.controller;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Reserva;
import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Vehiculo;
import com.uce.edu.avanzada.budget_rent_a_car.service.IReservaService;
import com.uce.edu.avanzada.budget_rent_a_car.service.IVehiculoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/reportes")
public class ReporteController {
	
	private static final Logger LOG = Logger.getLogger(ReporteController.class);
	@Autowired
	private IVehiculoService iVehiculoService;
	
	@Autowired
	private IReservaService iReservaService;
	
	//reporte Reservas
	@GetMapping("/reservas")
	public String reporteReservas(Reserva Reserva) {
		return "vReporteReserva";
	}

	@PostMapping("/listaReservas")
	public String listaReporteReservas(LocalDate fechaInicio, LocalDate fechaFinal, Model model) {
		System.out.println("fechas: " + fechaFinal + " " + fechaInicio);
		List<Reserva> lista = this.iReservaService.reporteReserva(fechaInicio, fechaFinal);
		model.addAttribute("reservas", lista);
		LOG.info("Generando reporte con éxito");
		return "vListaReporteReserva";
	}
	
}
