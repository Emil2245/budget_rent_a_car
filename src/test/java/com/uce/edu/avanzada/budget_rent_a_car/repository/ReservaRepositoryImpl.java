package com.uce.edu.avanzada.budget_rent_a_car.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Reserva;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Repository
@Transactional
public class ReservaRepositoryImpl implements IReservaRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional(value = TxType.MANDATORY)
	public void insertar(Reserva reserva) {
		this.entityManager.persist(reserva);

	}

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void actualizar(Reserva reserva) {
		this.entityManager.merge(reserva);
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Reserva> reporteReserva(LocalDate inicio, LocalDate fin) {
		TypedQuery<Reserva> query = this.entityManager.createQuery(
				"SELECT r FROM Reserva r JOIN r.vehiculo v WHERE r.fechaInicio "
				+ "BETWEEN :datoFechaInicio AND :datoFechaFinal AND r.fechaFin "
				+ "BETWEEN :datoFechaInicio AND :datoFechaFinal",
				Reserva.class);
		query.setParameter("datoFechaInicio", inicio.minusDays(1));
		query.setParameter("datoFechaFinal", fin.plusDays(1));
		return query.getResultList();
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public Reserva buscarCodigo(String codigo) {
		TypedQuery<Reserva> query = this.entityManager.createQuery(
				"SELECT r FROM Reserva r JOIN r.vehiculo v JOIN r.cliente c WHERE r.codigo = :datoCodigo",
				Reserva.class);
		query.setParameter("datoCodigo", codigo);
		return query.getSingleResult();
	}

}
