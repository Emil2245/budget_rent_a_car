package com.uce.edu.avanzada.budget_rent_a_car.repository;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Vehiculo;
import com.uce.edu.avanzada.budget_rent_a_car.repository.model.dto.VehiculoDTO;

import java.time.LocalDate;
import java.util.List;


public interface IVehiculoRepository {

    void insertar(Vehiculo vehiculo);

    Vehiculo buscar(Integer id);

    void actualizar(Vehiculo vehiculo);

    void eliminar(Integer id);

    Vehiculo buscarPlaca(String placa);

    Integer actualizarEstado(String placa);

   

    List<Vehiculo> buscar(String marca, String modelo);

    List<Vehiculo> reporteVehiculo(LocalDate fecha);

    List<Vehiculo> buscarTodos();
    List<Vehiculo> buscarTodosSoloDisponibles();


    List<Vehiculo> buscarAvaluo(String avaluo);

}
