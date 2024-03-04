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
    public void ingresar(Empleado empleado) {
        this.entityManager.persist(empleado);
    }

    @Override
    public Empleado buscar(Integer id) {
        return this.entityManager.find(Empleado.class, id);
    }

    @Override
    public Empleado buscarUser(String user) {
        TypedQuery<Empleado> query =
                this.entityManager
                        .createQuery("SELECT e FROM Empleado e WHERE e.usuario = :datoUsuario", Empleado.class)
                        .setParameter("datoUsuario", user);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            System.err.println("No se encontro el usuario");
            return null;

        }
    }

}
