package com.uce.edu.avanzada.budget_rent_a_car.service;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Vehiculo;

import java.util.List;

public interface IVehiculoService {

    void guardar(Vehiculo vehiculo);

    Vehiculo buscarPorId(Integer id);

    void actualizar(Vehiculo vehiculo);

    void borrar(Integer id);

    void borrarDisponible(Integer id, Boolean D);

    List<Vehiculo> buscarVehiculosPorMarcaYModelo(String marca, String modelo);

    Vehiculo buscarPlaca(String placa);

    List<Vehiculo> buscarTodos();

    List<Vehiculo> buscarTodosSoloDisponibles();

    Double calcularSubtotal(Vehiculo vehiculo);

    Double calcularTotal(Vehiculo vehiculo);

    List<Vehiculo> reporteAvaluo(String avaluo);

}
