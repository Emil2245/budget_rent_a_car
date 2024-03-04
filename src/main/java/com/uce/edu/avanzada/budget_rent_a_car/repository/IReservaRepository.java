package com.uce.edu.avanzada.budget_rent_a_car.repository;

import java.time.LocalDate;
import java.util.List;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Reserva;

public interface IReservaRepository {

	void insertar(Reserva reserva);

	void actualizar(Reserva reserva);

	List<Reserva> reporteReserva(LocalDate inicio, LocalDate fin);

	Reserva buscarCodigo(String codigo);
}
