package com.uce.edu.avanzada.budget_rent_a_car.repository;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Factura;

public interface IFacturaRepository {
	void insertar(Factura factura);
    void actualizar(Factura factura);
    void eliminar(Integer id);
    Factura seleccionar(Integer id);
}
