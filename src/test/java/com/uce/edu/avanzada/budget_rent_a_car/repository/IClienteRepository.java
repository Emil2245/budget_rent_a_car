package com.uce.edu.avanzada.budget_rent_a_car.repository;

import java.util.List;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Cliente;

public interface IClienteRepository {
	
	public void insertar(Cliente cliente);	
	
	public Cliente seleccionarPorCedula(String cedula);
	
	public List<Cliente> seleccionarTodos();

}
