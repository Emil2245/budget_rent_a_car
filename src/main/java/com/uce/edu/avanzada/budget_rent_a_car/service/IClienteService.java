package com.uce.edu.avanzada.budget_rent_a_car.service;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Cliente;

import java.util.List;

public interface IClienteService {
    void guardar(Cliente cliente);
    Cliente buscarPorCedula(String cedula);
    List<Cliente> buscarTodos();

}
