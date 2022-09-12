package com.uce.edu.demo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IReservaRepository;
import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.Cobro;
import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.Vehiculo;

@Service
public class ReservaServiceImpl implements IReservaService {

	@Autowired
	private IReservaRepository iReservaRepository;

	@Autowired
	private IVehiculoService iVehiculoService;

	@Autowired
	private IClienteService iClienteService;

	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public void reservarVehiculo(String placa, String cedula, LocalDateTime fechaInicio, LocalDateTime fechaFin,
			String numeroTarjeta) {
		// TODO Auto-generated method stub
		Vehiculo vehiculo = this.iVehiculoService.buscarPorPlaca(placa);

		Cliente cliente = this.iClienteService.buscarPorCedula(cedula);

		List<Reserva> reservasVehiculo = vehiculo.getReservas();

		List<Reserva> reservas = reservasVehiculo.stream().filter(r -> r.getFechaFin().compareTo(fechaInicio) <= 0)
				.toList();
		if (reservas.size() != 0) {
			throw new RuntimeException();
		}

		long noOfDaysBetween = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
		System.out.println(noOfDaysBetween);

		Reserva reserva = new Reserva();
		reserva.setFechaFin(fechaFin);
		reserva.setFechaInicio(fechaInicio);
		reserva.setCliente(cliente);
		reserva.setVehiculo(vehiculo);
		reserva.setSubtotal(vehiculo.getValorPorDia().multiply(new BigDecimal(noOfDaysBetween)).setScale(2));
		reserva.setValorIva(reserva.getSubtotal().multiply(new BigDecimal(0.12)).setScale(2));
		reserva.setValorTotal(reserva.getSubtotal().add(reserva.getValorIva()).setScale(2));
		reserva.setEstado("G");
		reserva.setNumeroTarjeta(numeroTarjeta);

		Cobro cobro = new Cobro();
		cobro.setNumeroTarjeta(numeroTarjeta);
		cobro.setValorIva(reserva.getValorIva());
		cobro.setValorSubtotal(reserva.getSubtotal());
		cobro.setValorTotal(cobro.getValorTotal());
		cobro.setReserva(reserva);

		reserva.setCobro(cobro);

		this.iReservaRepository.insertar(reserva);

	}
	
	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public BigDecimal calcularValorTotal(BigDecimal valorPorDia, LocalDateTime fechaInicio, LocalDateTime fechaFin) {

		long dias = ChronoUnit.DAYS.between(fechaInicio, fechaFin);

		BigDecimal subtotal = valorPorDia.multiply(new BigDecimal(dias)).setScale(2);

		BigDecimal iva = subtotal.multiply(new BigDecimal(0.12)).setScale(2);

		BigDecimal total = subtotal.add(iva);

		return total;
	}

}
