package com.uce.edu.avanzada.budget_rent_a_car.repository;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Vehiculo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

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
    public Vehiculo seleccionar(Integer id) {
        return this.entityManager.find(Vehiculo.class, id);
    }

    @Override
    @Transactional(value = TxType.MANDATORY)
    public void actualizar(Vehiculo vehiculo) {
        this.entityManager.merge(vehiculo);
    }

    @Override
    @Transactional(value = TxType.MANDATORY)
    public void eliminar(Integer id) {
        this.entityManager.remove(this.seleccionar(id));
    }

    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public Vehiculo buscarPlaca(String placa) {
        return this.entityManager
                .createQuery("SELECT e from Vehiculo e WHERE e.placa = :datoPlaca", Vehiculo.class)
                .setParameter("datoPlaca", placa)
                .getSingleResult();
    }

    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public List<Vehiculo> reporteVehiculo(LocalDate fecha) {
        return this.entityManager
                .createQuery("SELECT v FROM Vehiculo v JOIN v.reservas r WHERE MONTH(r.fechaInicio) = :datoMes", Vehiculo.class)
                .setParameter("datoMes", fecha.getMonthValue())
                .getResultList();

    }

    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public List<Vehiculo> seleccionarTodos() {
        return this.entityManager.createQuery("SELECT v FROM Vehiculo v", Vehiculo.class)
                .getResultList();
    }

    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public List<Vehiculo> seleccionarTodosSoloDisponibles() {
        return this.entityManager.createQuery("SELECT v FROM Vehiculo v WHERE v.estado != 'Indisponible'", Vehiculo.class)
                .getResultList();
    }

    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public List<Vehiculo> seleccionarTodos(String marca, String modelo) {
        return this.entityManager
                .createQuery("SELECT v FROM Vehiculo v WHERE v.marca=:datoMarca AND v.modelo=:datoModelo", Vehiculo.class)
                .setParameter("datoMarca", marca)
                .setParameter("datoModelo", modelo)
                .getResultList();
    }


    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public List<Vehiculo> seleccionarAvaluo(String avaluo) {
        return this.entityManager
                .createQuery("SELECT v FROM Vehiculo v WHERE v.avaluo >=:datoAvaluo", Vehiculo.class)
                .setParameter("datoAvaluo", avaluo)
                .getResultList();
    }

}
