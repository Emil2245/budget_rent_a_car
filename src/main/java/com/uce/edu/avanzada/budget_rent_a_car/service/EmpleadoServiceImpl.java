package com.uce.edu.avanzada.budget_rent_a_car.service;

import com.uce.edu.avanzada.budget_rent_a_car.repository.IEmpleadoRepository;
import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Empleado;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

    @Autowired
    private IEmpleadoRepository iEmpleadoRepository;
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void guardar(Empleado empleado) {
        this.iEmpleadoRepository.insertar(empleado);
    }

    @Override
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public Empleado buscarPorId(Integer id) {
        return this.iEmpleadoRepository.seleccionar(id);
    }

    public String siguienteVista(String user, String pass) {
        return "redirect:/inicio";
    }
}
