package com.uce.edu.avanzada.budget_rent_a_car.service;

import com.uce.edu.avanzada.budget_rent_a_car.repository.IFacturaRepository;
import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Factura;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacturaServiceImpl implements IFacturaService {
    @Autowired
    private IFacturaRepository iFacturaRepository;

    @Override
    @Transactional(value = TxType.REQUIRED)
    public void guardar(Factura factura) {
        this.iFacturaRepository.insertar(factura);
    }

    @Override
    @Transactional(value = TxType.REQUIRED)
    public void actualizar(Factura factura) {
        this.iFacturaRepository.actualizar(factura);
    }

    @Override
    @Transactional(value = TxType.REQUIRED)
    public void borrar(Integer id) {
        this.iFacturaRepository.eliminar(id);
    }

    @Override
    @Transactional(value = TxType.REQUIRED)
    public Factura buscar(Integer id) {
        return this.iFacturaRepository.seleccionar(id);
    }

}
