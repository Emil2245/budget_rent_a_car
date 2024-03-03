package com.uce.edu.avanzada.budget_rent_a_car.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Vehiculo;
import com.uce.edu.avanzada.budget_rent_a_car.repository.model.dto.VehiculoDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Repository
@Transactional
public class VehiculoRepositoryImpl implements IVehiculoRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void insertar(Vehiculo vehiculo) {
		this.entityManager.persist(vehiculo);
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Vehiculo buscarPlaca(String placa) {
		TypedQuery<Vehiculo> query = this.entityManager
				.createQuery("SELECT e from Vehiculo e WHERE e.placa = :datoPlaca", Vehiculo.class);
		query.setParameter("datoPlaca", placa);
		return query.getSingleResult();
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public VehiculoDTO buscarPorPlaca(String placa) {

		TypedQuery<VehiculoDTO> typedQuery = this.entityManager.createQuery(
				"SELECT new rent.car.modelo.dto.VehiculoDTO(e.placa, e.modelo,"
						+ "e.marca, e.anio, e.estado, e.valor) from Vehiculo e WHERE e.placa = :datoPlaca",
				VehiculoDTO.class);
		typedQuery.setParameter("datoPlaca", placa);
		return typedQuery.getSingleResult();
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Vehiculo> buscar(String marca, String modelo) {
		Query myQuery = this.entityManager.createQuery(
				"SELECT v FROM Vehiculo v WHERE v.marca=:datoMarca AND v.modelo=:datoModelo", Vehiculo.class);
		myQuery.setParameter("datoMarca", marca);
		myQuery.setParameter("datoModelo", modelo);
		return myQuery.getResultList();
	}

	@Override
	public List<Vehiculo> reporteVehiculo(LocalDate fecha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Vehiculo> buscarTodosDisponibles() {
		Query query = this.entityManager.createQuery("SELECT v FROM Vehiculo v");
		// query.setParameter("datoDisp", "D");

		return query.getResultList();
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Vehiculo> buscarAvaluo(String avaluo) {
		TypedQuery<Vehiculo> query = this.entityManager
				.createQuery("SELECT v FROM Vehiculo v WHERE v.avaluo >=:datoAvaluo", Vehiculo.class);
		query.setParameter("datoAvaluo", avaluo);
		return query.getResultList();
	}

}
