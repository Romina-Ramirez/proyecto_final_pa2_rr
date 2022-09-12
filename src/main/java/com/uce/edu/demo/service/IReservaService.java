package com.uce.edu.demo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.uce.edu.demo.repository.modelo.Reserva;

public interface IReservaService {
	
	public String reservarVehiculo(String placa, String cedula, LocalDateTime fechaInicio, LocalDateTime fechaFin, String numeroTarjeta);
		
	public BigDecimal calcularValorTotal(BigDecimal valorPorDia, LocalDateTime fechaInicio, LocalDateTime fechaFin);
	
	public List<Reserva> buscarPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);
	
	public List<Reserva> buscarClientesVip();
	
	public Reserva buscar(String numero);
	
	public void retirarVehiculo(String numero);
	
	public long calcularNumeroDias(LocalDateTime inicio, LocalDateTime fin);
	
	public String transformarFecha(LocalDateTime fecha);
}
