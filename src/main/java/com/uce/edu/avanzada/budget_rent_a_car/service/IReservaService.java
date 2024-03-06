package com.uce.edu.avanzada.budget_rent_a_car.service;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Reserva;
import com.uce.edu.avanzada.budget_rent_a_car.repository.model.dto.ReporteDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface IReservaService {

	void guardar(Reserva reserva);

	void actualizar(Reserva reserva);

	Reserva buscarPorId(Integer id);

	void eliminar(Integer id);

	Boolean verificarDisponibilidad(LocalDate inicio, LocalDate FIN, String placa);
	List<String> calcularIntervaloDias(LocalDate inicio, LocalDate FIN, String placa);

	List<Reserva> buscararReportesEntreFechas(LocalDate inicio, LocalDate fin);

	List<BigDecimal> calcularValorTotal(LocalDate inicio, LocalDate fin, String placa);

	String reservar(LocalDate inicio, LocalDate fin, String placa, String cedula, String tarjeta);

	void reservarEstado(String reserva);

	String getReserva(String codigo);
	void actualizarPorCodigoReserva(String codigoReserva);
	 List<ReporteDTO> listarReporteReservasDTO(LocalDate inicio, LocalDate fin);

}
