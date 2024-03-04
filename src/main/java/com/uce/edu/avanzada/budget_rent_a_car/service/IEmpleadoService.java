package com.uce.edu.avanzada.budget_rent_a_car.service;

import com.example.demo.repository.modelo.Empleado;

public interface IEmpleadoService {

	// CRUD
	public void ingresar(Empleado empleado);

	public Empleado buscarPorId(Integer id);
	
	//pasar siguiente vista 
	public String siguienteVista(String user, String pass);

}
