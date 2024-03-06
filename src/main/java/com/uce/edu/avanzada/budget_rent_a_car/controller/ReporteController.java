package com.uce.edu.avanzada.budget_rent_a_car.controller;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Reserva;
import com.uce.edu.avanzada.budget_rent_a_car.repository.model.dto.ReporteDTO;
import com.uce.edu.avanzada.budget_rent_a_car.service.IReservaService;
import com.uce.edu.avanzada.budget_rent_a_car.service.to.ReservaClienteTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/reportes")
public class ReporteController {

	@Autowired
	private IReservaService iReservaService;

	@GetMapping("/reservas")
	public String reporteReservas(Reserva Reserva) {
		return "vReporteReserva";
	}

	@PostMapping("/listaReservas")
	public String listaReporteReservas(LocalDate fechaInicio, LocalDate fechaFinal, Model model) {
		System.out.println("fechas: " + fechaFinal + " " + fechaInicio);
		List<Reserva> lista = this.iReservaService.reporteReserva(fechaInicio, fechaFinal);
		model.addAttribute("reservas", lista);
		return "vListaReporteReserva";
	}

	@GetMapping("/verReporteReserva")
	public String reporteReserva(ReservaClienteTO reservaClienteTO, Model model) {

		model.addAttribute("reservaClienteTO", new ReservaClienteTO());

		try {
			List<ReporteDTO> reporte = this.iReservaService.reporteDeReservasDTO(
					reservaClienteTO.getFechaInicioReserva(), reservaClienteTO.getFechaFinReserva());
			model.addAttribute("listaReporte", reporte);
		} catch (Exception e) {
			model.addAttribute("listaReporte", Arrays.asList(new ReporteDTO()));
		}
		return "reservas/vistaReporteReserva";

	}
}
