package com.uce.edu.avanzada.budget_rent_a_car.service;

import com.example.demo.repository.modelo.Factura;

public interface IFacturaService {
	public void guardar(Factura factura);
    public void actualizar(Factura factura); 
    public void borrar(Integer id);
    public Factura buscar(Integer id);
}
