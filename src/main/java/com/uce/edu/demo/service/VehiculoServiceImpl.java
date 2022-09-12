package com.uce.edu.demo.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IVehiculoRepository;
import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.repository.modelo.VehiculoReporte;

@Service
public class VehiculoServiceImpl implements IVehiculoService {

	@Autowired
	private IVehiculoRepository iVehiculoRepository;

	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public List<Vehiculo> buscarVehiculoDisponible(String marca, String modelo) {

		return this.iVehiculoRepository.buscar(modelo, marca);
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Vehiculo buscarPorPlaca(String placa) {
		// TODO Auto-generated method stub
		return this.iVehiculoRepository.buscarPorPlaca(placa);
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<VehiculoReporte> buscarVehiculosVip(String fecha) {
		List<Vehiculo> vehiculos = this.iVehiculoRepository.buscarVehiculosVip(fecha);
		List<VehiculoReporte> vehiculosReporte = new ArrayList<>();
		for (Vehiculo vehiculo : vehiculos) {
			VehiculoReporte vehi = new VehiculoReporte();
			vehi.setPlaca(vehiculo.getPlaca());
			vehi.setMarca(vehiculo.getMarca());
			vehi.setModelo(vehiculo.getModelo());
			vehi.setAvaluo(vehiculo.getAvaluo());
			vehi.setValorPorDia(vehiculo.getValorPorDia());
			BigDecimal subtotal = vehiculo.getReservas().parallelStream()
					.collect(Collectors.reducing(BigDecimal.ZERO, Reserva::getSubtotal, BigDecimal::add));
			vehi.setSubtotal(subtotal);
			BigDecimal valorTotal = vehiculo.getReservas().parallelStream()
					.collect(Collectors.reducing(BigDecimal.ZERO, Reserva::getValorTotal, BigDecimal::add));
			vehi.setValorTotal(valorTotal);
			if (vehiculosReporte.parallelStream().filter(v -> vehiculo.getPlaca().equals(v.getPlaca())).collect(Collectors.toList())
					.size() == 0)
				vehiculosReporte.add(vehi);
		}
		List<VehiculoReporte> reporteFinal = vehiculosReporte.stream()
				.sorted(Comparator.comparing(VehiculoReporte::getValorTotal).reversed()).collect(Collectors.toList());;

		return reporteFinal;
	}

	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public void insertarVehiculo(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		this.iVehiculoRepository.insertarVehiculo(vehiculo);
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Vehiculo> buscarMarca(String marca) {
		// TODO Auto-generated method stub
		return this.iVehiculoRepository.buscarMarca(marca);
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Vehiculo buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return this.iVehiculoRepository.buscarPorId(id);
	}

	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public void actualizar(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		this.iVehiculoRepository.actualizar(vehiculo);
	}

	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public void borrarVehiculo(Integer id) {
		// TODO Auto-generated method stub
		this.iVehiculoRepository.borrarVehiculo(id);
	}

}
