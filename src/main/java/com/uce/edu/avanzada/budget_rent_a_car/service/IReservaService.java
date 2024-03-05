package com.uce.edu.avanzada.budget_rent_a_car.service;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Reserva;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface IReservaService {

	void guardar(Reserva reserva);

	void actualizar(Reserva reserva);

	Reserva buscar(Integer id);

	void borrar(Integer id);

	Boolean verificar(LocalDate inicio, LocalDate FIN, String placa);
	List<String> calcularIntervaloDias(LocalDate inicio, LocalDate FIN, String placa);

	List<Reserva> reporteReserva(LocalDate inicio, LocalDate fin);

	List<BigDecimal> calcularValorTotal(LocalDate inicio, LocalDate fin, String placa);

	String reservar(LocalDate inicio, LocalDate fin, String placa, String cedula, String tarjeta);

	void aplicar(String reserva);

	String getReserva(String codigo);
	

}
