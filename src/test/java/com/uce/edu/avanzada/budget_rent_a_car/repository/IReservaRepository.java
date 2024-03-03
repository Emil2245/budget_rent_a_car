package com.uce.edu.avanzada.budget_rent_a_car.repository;

import java.time.LocalDate;
import java.util.List;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Reserva;

public interface IReservaRepository {

	public void insertar(Reserva reserva);

	public void actualizar(Reserva reserva);

	public List<Reserva> reporteReserva(LocalDate inicio, LocalDate fin);

	public Reserva buscarCodigo(String codigo);
}
