package com.uce.edu.avanzada.budget_rent_a_car.repository;

import java.time.LocalDate;
import java.util.List;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Vehiculo;
import com.uce.edu.avanzada.budget_rent_a_car.repository.model.dto.VehiculoDTO;

public interface IVehiculoRepository {

	void insertar(Vehiculo vehiculo);
	
	Vehiculo buscarPlaca(String placa);
	
	VehiculoDTO buscarPorPlaca(String placa);
	
	//Buscar vehiculos disponibles
	List<Vehiculo> buscar(String marca, String modelo);

	List<Vehiculo> reporteVehiculo(LocalDate fecha);
	List<Vehiculo> buscarTodosDisponibles();
	
	List<Vehiculo> buscarAvaluo(String avaluo);
}
