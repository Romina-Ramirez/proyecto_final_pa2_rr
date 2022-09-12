package com.uce.edu.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.uce.edu.demo.repository.modelo.Reserva;

public interface IReservaRepository {
	
	public void insertar(Reserva reserva);
	
	public void actualizar(Reserva reserva);
	
	public Reserva leer(String numero);
	
	public List<Reserva> buscarPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);
	
	public List<Reserva> buscarClientesVip();

}
