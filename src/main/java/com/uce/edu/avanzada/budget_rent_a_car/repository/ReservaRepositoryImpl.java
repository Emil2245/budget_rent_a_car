package com.uce.edu.avanzada.budget_rent_a_car.repository;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Reserva;
import com.uce.edu.avanzada.budget_rent_a_car.repository.model.dto.ReporteDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
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
    public Reserva seleccionarPorCodigo(String codigo) {
        return this.entityManager
                .createQuery("SELECT r FROM Reserva r JOIN r.vehiculo v JOIN r.cliente c WHERE r.codigo = :datoCodigo", Reserva.class)
                .setParameter("datoCodigo", codigo)
                .getSingleResult();
    }

    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public List<Reserva> seleccionarReportesEntreFechas(LocalDate inicio, LocalDate fin) {

        return this.entityManager
                .createQuery("SELECT r FROM Reserva r JOIN r.vehiculo v " +
                        "WHERE r.fechaInicio BETWEEN :datoFechaInicio AND :datoFechaFinal " +
                        "AND r.fechaFin BETWEEN :datoFechaInicio AND :datoFechaFinal", Reserva.class)
                .setParameter("datoFechaInicio", inicio.minusDays(1))
                .setParameter("datoFechaFinal", fin.plusDays(1))
                .getResultList();
    }

    @Override
    @Transactional(value = TxType.MANDATORY)
    public void eliminar(Integer id) {
        Reserva reserva = this.seleccionar(id);
        this.entityManager.remove(reserva);
    }


    @Override
    public void actualizarPorCodigoReserva(String codigoReserva) {

        this.entityManager.createQuery("UPDATE Reserva r SET r.estado='E' WHERE r.codigo=:codigoReserva")
                .setParameter("codigoReserva", codigoReserva)
                .executeUpdate();
    }

    @Override
    public List<ReporteDTO> reporteDeReservasDTO(LocalDate inicio, LocalDate fin) {
        String jpql = "SELECT new com.uce.edu.avanzada.budget_rent_a_car.repository.model.dto.ReporteDTO(c.cedula, c.nombre, c.apellido, v.placa, v.modelo, r.fechaInicio, r.fechaFin, r.subtotal, r.total, r.estado) FROM Reserva r JOIN r.cliente c JOIN r.vehiculo v "
                + "WHERE r.fechaInicio BETWEEN :datoFechaInicio AND :datoFechaFinal AND r.fechaFin "
                + "BETWEEN :datoFechaInicio AND :datoFechaFinal";

        return this.entityManager.createQuery(jpql, ReporteDTO.class)
        .setParameter("datoFechaInicio", inicio.minusDays(1))
        .setParameter("datoFechaFinal", fin.plusDays(1))
        .getResultList();
    }


}
