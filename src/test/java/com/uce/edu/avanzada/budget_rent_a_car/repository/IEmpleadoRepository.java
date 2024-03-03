package com.uce.edu.avanzada.budget_rent_a_car.repository;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Empleado;

public interface IEmpleadoRepository {

	public void ingresar(Empleado empleado);
	
	public Empleado seleccionarPorCedula(String cedula);
}
