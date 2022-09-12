package com.uce.edu.demo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface IReservaService {
	
	public void reservarVehiculo(String placa, String cedula, LocalDateTime fechaInicio, LocalDateTime fechaFin, String numeroTarjeta);
		
	public BigDecimal calcularValorTotal(BigDecimal valorPorDia, LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
