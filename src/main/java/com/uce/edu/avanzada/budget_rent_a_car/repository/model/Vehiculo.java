package com.uce.edu.avanzada.budget_rent_a_car.repository.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "vehiculo")
public class Vehiculo {

    @Id
    @SequenceGenerator(name = "seq_vehiculo", sequenceName = "seq_vehiculo", allocationSize = 1)
    @GeneratedValue(generator = "seq_vehiculo", strategy = GenerationType.SEQUENCE)
    @Column(name = "vehi_id")
    private Integer id;

    @Column(name = "vehi_placa")
    private String placa;

    @Column(name = "vehi_modelo")
    private String modelo;

    @Column(name = "vehi_marca")
    private String marca;

    @Column(name = "vehi_estado")
    private String estado;

    @Column(name = "vehi_anio_fabricacion")
    private String anioFabricacion;

    @Column(name = "vehi_cilindraje")
    private String cilindraje;

    @Column(name = "vehi_pais_fabricacion")
    private String paisFabricacion;

    @Column(name = "vehi_avaluo")
    private String avaluo;

    @Column(name = "vehi_valor_dia")
    private BigDecimal valorDia;

    @Column(name = "vehi_url_imagen")
    private String urlImagen;

    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL)
    private List<Reserva> reservas;

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }


    @Override
    public String toString() {
        return "Vehiculo{" +
                "id=" + id +
                ", placa='" + placa + '\'' +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                ", estado='" + estado + '\'' +
                ", anioFabricacion='" + anioFabricacion + '\'' +
                ", cilindraje='" + cilindraje + '\'' +
                ", paisFabricacion='" + paisFabricacion + '\'' +
                ", avaluo='" + avaluo + '\'' +
                ", valorDia=" + valorDia +
                ", urlImagen='" + urlImagen + '\'' +
                ", reservas=" + reservas +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getAnioFabricacion() {
        return anioFabricacion;
    }

    public void setAnioFabricacion(String anioFabricacion) {
        this.anioFabricacion = anioFabricacion;
    }

    public String getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(String cilindraje) {
        this.cilindraje = cilindraje;
    }

    public String getPaisFabricacion() {
        return paisFabricacion;
    }

    public void setPaisFabricacion(String paisFabricacion) {
        this.paisFabricacion = paisFabricacion;
    }

    public String getAvaluo() {
        return avaluo;
    }

    public void setAvaluo(String avaluo) {
        this.avaluo = avaluo;
    }

    public BigDecimal getValorDia() {
        return valorDia;
    }

    public void setValorDia(BigDecimal valorDia) {
        this.valorDia = valorDia;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
}
