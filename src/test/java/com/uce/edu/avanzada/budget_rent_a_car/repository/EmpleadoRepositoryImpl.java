package com.uce.edu.avanzada.budget_rent_a_car.repository;

import org.springframework.stereotype.Repository;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Empleado;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

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
	public Empleado seleccionarPorCedula(String cedula) {
		TypedQuery< Empleado> query = this.entityManager.createQuery("SELECT e FROM Empleado e WHERE e.cedula = :datoUsuario",Empleado.class);
		query.setParameter("datoUsuario", cedula);
		
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("No se encontro el usuario");
			return null;
			
		}
	}

}
