package com.uce.edu.avanzada.budget_rent_a_car.repository;

import java.util.List;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Cliente;

public interface IClienteRepository {
	
	void insertar(Cliente cliente);
	
	Cliente seleccionarPorCedula(String cedula);
	
	List<Cliente> seleccionarTodos();

}
