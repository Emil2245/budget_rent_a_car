package com.uce.edu.avanzada.budget_rent_a_car.repository;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Reserva;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Transactional
@Repository
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
    public Reserva seleccionar(Integer id) {
        return this.entityManager.find(Reserva.class, id);
    }

    @Override
    @Transactional(value = TxType.MANDATORY)
    public void eliminar(Integer id) {
        Reserva reserva = this.seleccionar(id);
        this.entityManager.remove(reserva);

    }

    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public List<Reserva> reporteReserva(LocalDate inicio, LocalDate fin) {

        return this.entityManager
                .createQuery("SELECT r FROM Reserva r JOIN r.vehiculo v WHERE r.fechaInicio "
                        + "BETWEEN :datoFechaInicio AND :datoFechaFinal AND r.fechaFin "
                        + "BETWEEN :datoFechaInicio AND :datoFechaFinal", Reserva.class)
                .setParameter("datoFechaInicio", inicio.minusDays(1))
                .setParameter("datoFechaFinal", fin.plusDays(1))
                .getResultList();
    }

    @Override
    public Reserva buscarCodigo(String codigo) {
        return this.entityManager
                .createQuery("SELECT r FROM Reserva r JOIN r.vehiculo v JOIN r.cliente c WHERE r.codigo = :datoCodigo", Reserva.class)
                .setParameter("datoCodigo", codigo)
                .getSingleResult();
    }

    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public List<Reserva> buscarClientesVip() {

        List<Reserva> reservas = this.entityManager
                .createQuery("SELECT r FROM Reserva r JOIN r.cliente c ORDER BY r.total DESC", Reserva.class)
                .getResultList();
        reservas.parallelStream().forEach(r -> r.getCliente().getCedula());
        return reservas;
    }

}
