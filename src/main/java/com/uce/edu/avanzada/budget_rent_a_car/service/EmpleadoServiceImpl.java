package com.uce.edu.avanzada.budget_rent_a_car.service;

import com.uce.edu.avanzada.budget_rent_a_car.repository.IEmpleadoRepository;
import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

    @Autowired
    private IEmpleadoRepository empleadoRepository;
    @Override
    public void ingresar(Empleado empleado) {
        this.empleadoRepository.ingresar(empleado);
    }

    @Override
    public Empleado buscarPorId(Integer id) {
        return this.buscarPorId(id);
    }

    public String siguienteVista(String user, String pass) {
        return "redirect:/inicio";
    }
}
