package com.uce.edu.avanzada.budget_rent_a_car.repository;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Reserva;

import java.time.LocalDate;
import java.util.List;

public interface IReservaRepository {

    void insertar(Reserva reserva);

    void actualizar(Reserva reserva);

    Reserva seleccionar(Integer id);

    void eliminar(Integer id);

    List<Reserva> reporteReserva(LocalDate inicio, LocalDate fin);

    Reserva buscarCodigo(String codigo);

    List<Reserva> buscarClientesVip();

}
