package com.uce.edu.avanzada.budget_rent_a_car.repository;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class ClienteRepositoryImpl implements IClienteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(value = TxType.MANDATORY)
    public void insertar(Cliente cliente) {
        this.entityManager.persist(cliente);
    }

    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public Cliente seleccionarPorCedula(String cedula) {
        return this.entityManager
                .createQuery("SELECT c FROM Cliente c WHERE c.cedula=:datoCedula", Cliente.class)
                .setParameter("datoCedula", cedula)
                .getSingleResult();
    }

    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public List<Cliente> seleccionarTodosClientes() {
        return this.entityManager
                .createQuery("SELECT c FROM Cliente c", Cliente.class)
                .getResultList();
    }

}
