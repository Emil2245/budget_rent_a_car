package com.uce.edu.avanzada.budget_rent_a_car.service;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Vehiculo;
import com.uce.edu.avanzada.budget_rent_a_car.repository.model.dto.VehiculoDTO;

import java.time.LocalDate;
import java.util.List;

public interface IVehiculoService {

	void guardar(Vehiculo vehiculo);

	Vehiculo encontrar(Integer id);

	void actualizar(Vehiculo vehiculo);

	void borrar(Integer id);

	// BORRAR DISPONIBLE
	void borrarDisponible(Integer id, Boolean D);

	// Buscar vehiculosDisponibles
	List<Vehiculo> buscarVehiculosPorMarcaYModelo(String marca, String modelo);

	// BUSCAR PLACA
	Vehiculo buscarPlaca(String placa);
	
	

	List<Vehiculo> encontrarTodos();

	// Vehiculos VIP
	List<Vehiculo> vehiculosVIP(LocalDate fecha);

	List<Vehiculo> buscarTodos();
	List<Vehiculo> buscarTodosSoloDisponibles();

	Double calcularSubtotal(Vehiculo vehiculo);

	Double calcularTotal(Vehiculo vehiculo);
	
	List<Vehiculo> reporteAvaluo(String avaluo);

}
