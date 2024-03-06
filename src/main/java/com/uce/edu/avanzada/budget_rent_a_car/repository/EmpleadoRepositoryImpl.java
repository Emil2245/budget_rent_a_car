package com.uce.edu.avanzada.budget_rent_a_car.repository;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Empleado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class EmpleadoRepositoryImpl implements IEmpleadoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(Transactional.TxType.MANDATORY)
    public void insertar(Empleado empleado) {
        this.entityManager.persist(empleado);
    }

    @Override
    public Empleado seleccionar(Integer id) {
        return this.entityManager.find(Empleado.class, id);
    }


}
