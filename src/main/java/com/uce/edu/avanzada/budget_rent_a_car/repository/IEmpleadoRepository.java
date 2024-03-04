package com.uce.edu.avanzada.budget_rent_a_car.repository;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Empleado;

public interface IEmpleadoRepository {
	void ingresar(Empleado empleado);

	Empleado buscar(Integer id);

	// BUSCAR POR USERNAME
	Empleado buscarUser(String user);

}
