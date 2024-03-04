package com.uce.edu.avanzada.budget_rent_a_car.service;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Cliente;

import java.util.List;

public interface IClienteService {
	void crear(Cliente cliente);
	void actualizar(Cliente cliente);
	void borrar(Integer id);
	Cliente buscarPorCedula(String cedula);
	Cliente buscarPorId(Integer id);
	List<Cliente> verClientes();

}
