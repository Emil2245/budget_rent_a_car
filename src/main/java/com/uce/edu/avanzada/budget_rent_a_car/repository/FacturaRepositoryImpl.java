package com.uce.edu.avanzada.budget_rent_a_car.repository;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Factura;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class FacturaRepositoryImpl implements IFacturaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(value = TxType.MANDATORY)
    public void insertar(Factura factura) {
        this.entityManager.persist(factura);

    }

    @Override
    @Transactional(value = TxType.MANDATORY)
    public void actualizar(Factura factura) {
        this.entityManager.merge(factura);

    }

    @Override
    @Transactional(value = TxType.MANDATORY)
    public void eliminar(Integer id) {
        this.entityManager.remove(this.seleccionar(id));
    }

    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public Factura seleccionar(Integer id) {
        return this.entityManager.find(Factura.class, id);
    }

}
