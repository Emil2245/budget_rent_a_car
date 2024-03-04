package com.uce.edu.avanzada.budget_rent_a_car.repository;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Cliente;

import java.util.List;

public interface IClienteRepository {

    void insertar(Cliente cliente);
    Cliente buscarPorCedula(String cedula);

    Cliente seleccionarPorCedula(String cedula);

    List<Cliente> seleccionarTodos();

}
