package com.uce.edu.avanzada.budget_rent_a_car.service;

import com.uce.edu.avanzada.budget_rent_a_car.repository.IVehiculoRepository;
import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Reserva;
import com.uce.edu.avanzada.budget_rent_a_car.repository.model.Vehiculo;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehiculoServiceImpl implements IVehiculoService {

	@Autowired
	private IVehiculoRepository vehiculoRepository;

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void guardar(Vehiculo vehiculo) {
		this.vehiculoRepository.insertar(vehiculo);
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Vehiculo buscarPorId(Integer id) {
		return this.vehiculoRepository.seleccionar(id);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void actualizar(Vehiculo vehiculo) {
		this.vehiculoRepository.actualizar(vehiculo);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void borrar(Integer id) {
		this.vehiculoRepository.eliminar(id);
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Vehiculo buscarPlaca(String placa) {
		return this.vehiculoRepository.buscarPlaca(placa);
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Vehiculo> buscarTodos() {
		return this.vehiculoRepository.seleccionarTodos();
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Vehiculo> buscarTodosSoloDisponibles() {
		return this.vehiculoRepository.seleccionarTodosSoloDisponibles();
	}

	@Override
	public Double calcularSubtotal(Vehiculo vehiculo) {
		List<Reserva> lista = vehiculo.getReservas();
		Double subTotal = lista.parallelStream().map(b -> b.getSubtotal().doubleValue()).reduce(0.0, Double::sum);
		return subTotal * 0.15;
	}

	@Override
	public Double calcularTotal(Vehiculo vehiculo) {
		List<Reserva> lista = vehiculo.getReservas();
        return lista.parallelStream()
				.map(b -> b.getTotal().doubleValue())
				.reduce(0.0, Double::sum);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void borrarDisponible(Integer id, Boolean D) {
		if (D) {
			borrar(id);
		} else {
			System.out.println("Vehículo no disponible, no es posible eliminar.");
		}
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Vehiculo> buscarVehiculosPorMarcaYModelo(String marca, String modelo) {
		return this.vehiculoRepository.seleccionarTodos(marca, modelo);
	}


	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Vehiculo> reporteAvaluo(String avaluo) {
		return this.vehiculoRepository.seleccionarAvaluo(avaluo);
	}

}
