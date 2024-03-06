package com.uce.edu.avanzada.budget_rent_a_car.service;

import com.uce.edu.avanzada.budget_rent_a_car.repository.IClienteRepository;
import com.uce.edu.avanzada.budget_rent_a_car.repository.IFacturaRepository;
import com.uce.edu.avanzada.budget_rent_a_car.repository.IReservaRepository;
import com.uce.edu.avanzada.budget_rent_a_car.repository.IVehiculoRepository;
import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Cliente;
import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Factura;
import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Reserva;
import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Vehiculo;
import com.uce.edu.avanzada.budget_rent_a_car.repository.model.dto.ReporteDTO;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ReservaServiceImpl implements IReservaService {

    @Autowired
    private IReservaRepository iReservaRepository;

    @Autowired
    private IVehiculoRepository iVehiculoRepository;

    @Autowired
    private IClienteRepository iClienteRepository;

    @Autowired
    private IFacturaRepository iFacturaRepository;

    @Override
    @Transactional(value = TxType.REQUIRED)
    public void guardar(Reserva reserva) {
        this.iReservaRepository.insertar(reserva);
    }

    @Override
    @Transactional(value = TxType.REQUIRED)
    public void actualizar(Reserva reserva) {
        this.iReservaRepository.actualizar(reserva);
    }

    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public Reserva buscar(Integer id) {
        return this.iReservaRepository.seleccionar(id);
    }

    @Override
    @Transactional(value = TxType.REQUIRED)
    public void borrar(Integer id) {
        this.iReservaRepository.eliminar(id);
    }

    @Override
    @Transactional(value = TxType.REQUIRED)
    public Boolean verificar(LocalDate inicio, LocalDate fin, String placa) {
        Vehiculo v = this.iVehiculoRepository.buscarPlaca(placa);
        List<Reserva> reservas = this.iReservaRepository.seleccionarReportesEntreFechas(inicio, fin);

        return reservas.parallelStream().anyMatch(x -> x.getVehiculo().getId().equals(v.getId()));
    }

    @Override
    @Transactional(value = TxType.REQUIRED)
    public List<String> calcularIntervaloDias(LocalDate inicio, LocalDate fin, String placa) {
        Vehiculo v = this.iVehiculoRepository.buscarPlaca(placa);
        List<Reserva> reservas = this.iReservaRepository.seleccionarReportesEntreFechas(inicio, fin);
        return reservas.parallelStream()
                .filter(reserva -> reserva.getVehiculo().getId().equals(v.getId()))
                .map(reserva ->  "\nDesde: "+reserva.getFechaInicio()+" / Hasta: "+ reserva.getFechaFin()+ "\n")
                .toList();
    }

    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public List<Reserva> reporteReserva(LocalDate inicio, LocalDate fin) {
        return this.iReservaRepository.seleccionarReportesEntreFechas(inicio, fin);
    }

    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public List<BigDecimal> calcularValorTotal(LocalDate inicio, LocalDate fin, String placa) {
        long dias = ChronoUnit.DAYS.between(inicio, fin);
        Vehiculo vehiculo = this.iVehiculoRepository.buscarPlaca(placa);
        BigDecimal subtotal = vehiculo.getValorDia().multiply(new BigDecimal(dias)).setScale(2, RoundingMode.UP);
        BigDecimal ice = subtotal.multiply(new BigDecimal(0.15)).setScale(2, RoundingMode.UP);
        BigDecimal total = subtotal.add(ice).setScale(2, RoundingMode.UP);
        List<BigDecimal> calculos = new ArrayList<>();
        calculos.add(subtotal);
        calculos.add(ice);
        calculos.add(total);
        return calculos;

    }

    @Override
    @Transactional(value = TxType.REQUIRED)
    public String reservar(LocalDate inicio, LocalDate fin, String placa, String cedula, String tarjeta) {
        Vehiculo vehiculo = this.iVehiculoRepository.buscarPlaca(placa);
        Cliente cliente = this.iClienteRepository.seleccionarPorCedula(cedula);

        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
        Random random = new Random();
        reserva.setCodigo("R" + random.nextInt(100));
        reserva.setEstado("G");
        var calculos = this.calcularValorTotal(inicio, fin, placa);
        reserva.setFechaInicio(inicio);
        reserva.setFechaFin(fin);
        reserva.setIce(calculos.get(1));
        reserva.setNumTarjeta(tarjeta);
        reserva.setSubtotal(calculos.get(0));
        reserva.setTotal(calculos.get(2));
        reserva.setVehiculo(vehiculo);
        Factura factura = new Factura();
        factura.setIva(calculos.get(1));
        factura.setNumTarjeta(tarjeta);
        factura.setSubtotal(calculos.get(0));
        factura.setTotal(calculos.get(2));
        factura.setReserva(reserva);
        reserva.setFactura(factura);
        this.iFacturaRepository.insertar(factura);
        this.guardar(reserva);
        return reserva.getCodigo();
    }

    @Override
    @Transactional(value = TxType.REQUIRED)
    public void aplicar(String codigo) {
        Reserva reserva = this.iReservaRepository.seleccionarPorCodigo(codigo);
        Vehiculo vehiculo = reserva.getVehiculo();

        reserva.setEstado("E");
        vehiculo.setEstado("Indisponible");

        this.iVehiculoRepository.actualizar(vehiculo);
        this.iReservaRepository.actualizar(reserva);

    }

    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public String getReserva(String codigo) {
        Reserva reserva = this.iReservaRepository.seleccionarPorCodigo(codigo);

        return String.format("Placa: %s - Modelo: %s – Estado: %s – Fecha: %s-%s – Reservado por: %s",
                reserva.getVehiculo().getPlaca(), reserva.getVehiculo().getModelo(),
                reserva.getVehiculo().getEstado().equals("D") ? "Disponible" : "Indisponible", reserva.getFechaInicio(),
                reserva.getFechaFin(), reserva.getCliente().getCedula());
    }

	@Override
	public void retirar(String codigoReserva) {
		this.iReservaRepository.actualizarPorCodigoReserva(codigoReserva);
		
	}

	@Override
	public List<ReporteDTO> reporteDeReservasDTO(LocalDate inicio, LocalDate fin) {
		// TODO Auto-generated method stub
		return this.iReservaRepository.reporteDeReservasDTO(inicio, fin);
	}

}
