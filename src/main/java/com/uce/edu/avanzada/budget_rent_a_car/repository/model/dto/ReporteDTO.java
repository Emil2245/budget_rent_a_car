package com.uce.edu.avanzada.budget_rent_a_car.repository.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReporteDTO {
	private String cedula;
	private String nombre;
	private String apellido;
	private String placa;
	private String modelo;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private BigDecimal subtotal;
	private BigDecimal total;
	private String estado;
	//SET Y GET
	
	
	public String getCedula() {
		return cedula;
	}
	public ReporteDTO() {
		super();
		
	}
	
	public ReporteDTO(String cedula, String nombre, String apellido, String placa, String modelo, LocalDate fechaInicio,
			LocalDate fechaFin, BigDecimal subtotal, BigDecimal total, String estado) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.placa = placa;
		this.modelo = modelo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.subtotal = subtotal;
		this.total = total;
		this.estado = estado;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDate getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
