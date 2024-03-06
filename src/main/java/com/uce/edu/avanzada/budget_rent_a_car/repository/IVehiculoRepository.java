package com.uce.edu.avanzada.budget_rent_a_car.repository;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Vehiculo;

import java.time.LocalDate;
import java.util.List;


public interface IVehiculoRepository {

    void insertar(Vehiculo vehiculo);
    Vehiculo seleccionar(Integer id);
    void actualizar(Vehiculo vehiculo);
    void eliminar(Integer id);
    Vehiculo buscarPlaca(String placa);
    List<Vehiculo> seleccionarTodos(String marca, String modelo);
    List<Vehiculo> reporteVehiculo(LocalDate fecha);
    List<Vehiculo> seleccionarTodos();
    List<Vehiculo> seleccionarTodosSoloDisponibles();
    List<Vehiculo> seleccionarAvaluo(String avaluo);

}
