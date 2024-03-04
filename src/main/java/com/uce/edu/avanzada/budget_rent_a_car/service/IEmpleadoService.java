package com.uce.edu.avanzada.budget_rent_a_car.service;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Empleado;

public interface IEmpleadoService {
    void ingresar(Empleado empleado);

    Empleado buscarPorId(Integer id);
    String siguienteVista(String user, String pass);

}
