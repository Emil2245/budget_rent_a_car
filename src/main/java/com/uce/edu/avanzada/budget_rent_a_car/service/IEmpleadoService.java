package com.uce.edu.avanzada.budget_rent_a_car.service;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Empleado;

public interface IEmpleadoService {

	// CRUD
	void ingresar(Empleado empleado);

	Empleado buscarPorId(Integer id);
	
	//pasar siguiente vista 
	String siguienteVista(String user, String pass);

}
