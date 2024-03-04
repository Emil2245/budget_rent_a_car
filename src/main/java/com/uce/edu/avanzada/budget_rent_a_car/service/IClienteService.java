package com.uce.edu.avanzada.budget_rent_a_car.service;

import com.example.demo.repository.modelo.Cliente;

import java.util.List;

public interface IClienteService {
	public void crear(Cliente cliente);	
	public void actualizar(Cliente cliente);
	public void borrar(Integer id);
	public Cliente buscarPorCedula(String cedula);
	public Cliente buscarPorId(Integer id);
	public List<Cliente> verClientes();

}
