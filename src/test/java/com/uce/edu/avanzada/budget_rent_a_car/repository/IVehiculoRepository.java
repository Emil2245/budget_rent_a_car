package com.uce.edu.avanzada.budget_rent_a_car.repository;

import java.time.LocalDate;
import java.util.List;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Vehiculo;
import com.uce.edu.avanzada.budget_rent_a_car.repository.model.dto.VehiculoDTO;

public interface IVehiculoRepository {

	public void insertar(Vehiculo vehiculo);
	
	public Vehiculo buscarPlaca(String placa);
	
	public VehiculoDTO buscarPorPlaca(String placa);
	
	//Buscar vehiculos disponibles
	public List<Vehiculo> buscar(String marca, String modelo);

	public List<Vehiculo> reporteVehiculo(LocalDate fecha);
	public List<Vehiculo> buscarTodosDisponibles();
	
	public List<Vehiculo> buscarAvaluo(String avaluo);
}
