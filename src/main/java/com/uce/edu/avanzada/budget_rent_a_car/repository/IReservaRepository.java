package com.uce.edu.avanzada.budget_rent_a_car.repository;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Reserva;
import com.uce.edu.avanzada.budget_rent_a_car.repository.model.dto.ReporteDTO;

import java.time.LocalDate;
import java.util.List;

public interface IReservaRepository {

    void insertar(Reserva reserva);

    void actualizar(Reserva reserva);

    Reserva seleccionar(Integer id);

    void eliminar(Integer id);
    
    void actualizarPorCodigoReserva(String codigoReserva);

    List<Reserva> seleccionarReportesEntreFechas(LocalDate inicio, LocalDate fin);

    Reserva seleccionarPorCodigo(String codigo);

    List<ReporteDTO> reporteDeReservasDTO(LocalDate inicio, LocalDate fin);

}
