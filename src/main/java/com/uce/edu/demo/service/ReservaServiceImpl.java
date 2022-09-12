package com.uce.edu.demo.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IReservaRepository;
import com.uce.edu.demo.repository.IVehiculoRepository;
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
	private IVehiculoRepository iVehiculoRepository;

	@Autowired
	private IClienteService iClienteService;

	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public String reservarVehiculo(String placa, String cedula, LocalDateTime fechaInicio, LocalDateTime fechaFin,
			String numeroTarjeta) {

		List<Reserva> reservasFinal = new ArrayList<>();

		Vehiculo vehiculo = this.iVehiculoService.buscarPorPlaca(placa);

		Cliente cliente = this.iClienteService.buscarPorCedula(cedula);

		List<Reserva> reservasVehiculo = vehiculo.getReservas();

		List<Reserva> reservas = reservasVehiculo.stream().filter(r -> fechaInicio.compareTo(r.getFechaFin()) <=0).sorted(Comparator.comparing(Reserva::getFechaFin)).collect(Collectors.toList());

		
		if (reservas.size() != 0) {
			LocalDateTime fecha = reservas.get(reservas.size()-1).getFechaFin();
			
			String fechaString = this.transformarFecha(fecha);
			return "No"+fechaString;
		}

		long noOfDaysBetween = this.calcularNumeroDias(fechaInicio, fechaFin);
		System.out.println(noOfDaysBetween);

		Reserva reserva = new Reserva();
		reserva.setFechaFin(fechaFin);
		reserva.setFechaInicio(fechaInicio);
		reserva.setSubtotal(
				vehiculo.getValorPorDia().multiply(new BigDecimal(noOfDaysBetween)).setScale(2, RoundingMode.UP));
		reserva.setValorIva(reserva.getSubtotal().multiply(new BigDecimal(0.12)).setScale(2, RoundingMode.UP));
		reserva.setValorTotal(reserva.getSubtotal().add(reserva.getValorIva()).setScale(2, RoundingMode.UP));
		reserva.setEstado("G");
		reserva.setNumeroTarjeta(numeroTarjeta);

		Cobro cobro = new Cobro();
		cobro.setNumeroTarjeta(numeroTarjeta);
		cobro.setValorIva(reserva.getValorIva());
		cobro.setValorSubtotal(reserva.getSubtotal());
		cobro.setValorTotal(reserva.getValorTotal());
		cobro.setReserva(reserva);

		reserva.setCobro(cobro);

		reservasFinal.add(reserva);
		cliente.setReservas(reservasFinal);
		vehiculo.setReservas(reservasFinal);

		reserva.setCliente(cliente);
		reserva.setVehiculo(vehiculo);

		this.iReservaRepository.insertar(reserva);

		String numero = cedula.substring(0, 3) + placa.substring(0, 3) + reserva.getId();
		reserva.setNumero(numero);
		this.iReservaRepository.actualizar(reserva);

		return reserva.getNumero();

	}

	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public BigDecimal calcularValorTotal(BigDecimal valorPorDia, LocalDateTime fechaInicio, LocalDateTime fechaFin) {

		long dias= this.calcularNumeroDias(fechaInicio, fechaFin);

		BigDecimal subtotal = valorPorDia.multiply(new BigDecimal(dias)).setScale(2, RoundingMode.UP);

		BigDecimal iva = subtotal.multiply(new BigDecimal(0.12)).setScale(2, RoundingMode.UP);

		BigDecimal total = subtotal.add(iva).setScale(2, RoundingMode.UP);

		return total;
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Reserva> buscarPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {

		return this.iReservaRepository.buscarPorRangoFechas(fechaInicio, fechaFin);
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Reserva> buscarClientesVip() {
		// TODO Auto-generated method stub
		return this.iReservaRepository.buscarClientesVip();
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Reserva buscar(String numero) {
		return this.iReservaRepository.leer(numero);
	}

	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public void retirarVehiculo(String numero) {
		Reserva r = this.iReservaRepository.leer(numero);
		Vehiculo v = r.getVehiculo();
		v.setEstado("ND");
		r.setEstado("E");

		this.iReservaRepository.actualizar(r);
		this.iVehiculoRepository.actualizar(v);
	}

	@Override
	public long calcularNumeroDias(LocalDateTime inicio, LocalDateTime fin) {
		return ChronoUnit.DAYS.between(inicio, fin);
	}
	
	@Override
	public String transformarFecha(LocalDateTime fecha) {
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	return fecha.format(dateTimeFormatter);
	}
}
