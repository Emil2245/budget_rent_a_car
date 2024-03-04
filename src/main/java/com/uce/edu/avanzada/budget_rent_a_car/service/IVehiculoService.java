package com.uce.edu.avanzada.budget_rent_a_car.service;

import com.example.demo.repository.modelo.Vehiculo;

import java.time.LocalDate;
import java.util.List;

public interface IVehiculoService {

	public void guardar(Vehiculo vehiculo);

	public Vehiculo encontrar(Integer id);

	public void actualizar(Vehiculo vehiculo);

	public void borrar(Integer id);

	// BORRAR DISPONIBLE
	public void borrarDisponible(Integer id, Boolean D);

	// Buscar vehiculosDisponibles
	public List<Vehiculo> buscarVehiculosDisponibles(String marca, String modelo);

	// BUSCAR PLACA
	public Vehiculo buscarPlaca(String placa);

	public List<Vehiculo> encontrarTodos();

	// Vehiculos VIP
	public List<Vehiculo> vehiculosVIP(LocalDate fecha);

	public List<Vehiculo> buscarTodosDisponibles();

	public Double calcularSubtotal(Vehiculo vehiculo);

	public Double calcularTotal(Vehiculo vehiculo);
	
	public List<Vehiculo> reporteAvaluo(String avaluo);

}
