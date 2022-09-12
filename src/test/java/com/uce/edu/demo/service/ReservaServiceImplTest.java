package com.uce.edu.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.Vehiculo;

@SpringBootTest
class ReservaServiceImplTest {

	@Autowired
	private IReservaService reservaService;

	@Test
	@Transactional
	void testBuscar() {
		Reserva reserva = this.reservaService.buscar("172A151");
		assertEquals("12", reserva.getNumeroTarjeta(), "Los datos no son iguales");
	}

	@Test
	@Transactional
	@Rollback(true)
	void testRetirarVehiculo() {
		Reserva reserva = this.reservaService.buscar("172A151");
		this.reservaService.retirarVehiculo(reserva.getNumero());

		assertEquals("E", this.reservaService.buscar("172A151").getEstado());
	}

	@Test
	@Transactional
	@Rollback(true)
	void testReservarVehiculo() {
		this.reservaService.reservarVehiculo("A15-457", "1723069801", LocalDateTime.of(2022, 12, 10, 14, 12),
				LocalDateTime.of(2022, 12, 14, 14, 12), "12");

		assertNotNull(this.reservaService.buscar("172A152"));
	}

	@Test
	@Transactional
	@Rollback(true)
	void testCalcularValorTotal() {
		Reserva reserva = this.reservaService.buscar("172A151");
		Vehiculo v = reserva.getVehiculo();
		BigDecimal valor = this.reservaService.calcularValorTotal(v.getValorPorDia(), reserva.getFechaInicio(),
				reserva.getFechaFin());

		assertEquals(new BigDecimal(112).setScale(2, RoundingMode.UP), valor.setScale(2, RoundingMode.UP));
	}

}
