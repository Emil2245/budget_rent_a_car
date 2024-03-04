package com.uce.edu.avanzada.budget_rent_a_car.service.to;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReservaClienteTO {
    private String cedulaCliente;
    private String placaVehiculo;
    private String marcaVehiculo;
    private String modeloVehiculo;
    private LocalDate fechaInicioReserva;
    private LocalDate fechaFinReserva;
    private String numTargetaReserva;
    private BigDecimal totalReserva;
    private String codigoReserva;

    // GET Y SET
    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getMarcaVehiculo() {
        return marcaVehiculo;
    }

    public void setMarcaVehiculo(String marcaVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
    }

    public String getModeloVehiculo() {
        return modeloVehiculo;
    }

    public void setModeloVehiculo(String modeloVehiculo) {
        this.modeloVehiculo = modeloVehiculo;
    }

    public LocalDate getFechaInicioReserva() {
        return fechaInicioReserva;
    }

    public void setFechaInicioReserva(LocalDate fechaInicioReserva) {
        this.fechaInicioReserva = fechaInicioReserva;
    }

    public LocalDate getFechaFinReserva() {
        return fechaFinReserva;
    }

    public void setFechaFinReserva(LocalDate fechaFinReserva) {
        this.fechaFinReserva = fechaFinReserva;
    }

    public String getNumTargetaReserva() {
        return numTargetaReserva;
    }

    public void setNumTargetaReserva(String numTargetaReserva) {
        this.numTargetaReserva = numTargetaReserva;
    }

    public BigDecimal getTotalReserva() {
        return totalReserva;
    }

    public void setTotalReserva(BigDecimal totalReserva) {
        this.totalReserva = totalReserva;
    }

    public String getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    @Override
    public String toString() {
        return "ReservaClienteTO{" +
                "cedulaCliente='" + cedulaCliente + '\'' +
                ", placaVehiculo='" + placaVehiculo + '\'' +
                ", marcaVehiculo='" + marcaVehiculo + '\'' +
                ", modeloVehiculo='" + modeloVehiculo + '\'' +
                ", fechaInicioReserva=" + fechaInicioReserva +
                ", fechaFinReserva=" + fechaFinReserva +
                ", numTargetaReserva='" + numTargetaReserva + '\'' +
                ", totalReserva=" + totalReserva +
                '}';
    }
}
