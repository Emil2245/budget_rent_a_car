package com.uce.edu.avanzada.budget_rent_a_car.service;

import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Factura;

public interface IFacturaService {
	void guardar(Factura factura);
    void actualizar(Factura factura);
    void eliminar(Integer id);
    Factura buscar(Integer id);
}
