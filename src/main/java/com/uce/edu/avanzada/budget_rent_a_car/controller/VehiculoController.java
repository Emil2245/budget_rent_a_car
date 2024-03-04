package com.uce.edu.avanzada.budget_rent_a_car.controller;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Vehiculo;
import com.uce.edu.avanzada.budget_rent_a_car.service.IVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/vehiculos")
public class VehiculoController {
	
	@Autowired
	private IVehiculoService iVehiculoService;
	
	@GetMapping("/avaluo")
	public String reporteAvaluo(Vehiculo vehiculo) {
		return "vReporteAvaluo";
	}

	@PostMapping("/listaAvaluo")
	public String listaReporteAvaluo(String avaluo, Model model) {
		List<Vehiculo> lista = this.iVehiculoService.reporteAvaluo(avaluo);
		model.addAttribute("lista", lista);
		return "vListaReporteAvaluo";
	}

}
