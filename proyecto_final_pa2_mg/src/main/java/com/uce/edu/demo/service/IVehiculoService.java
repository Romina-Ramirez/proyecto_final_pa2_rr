package com.uce.edu.demo.service;

import java.util.List;

import com.uce.edu.demo.repository.modelo.Vehiculo;

public interface IVehiculoService {
	
	public List<Vehiculo> buscarVehiculoDisponible(String marca, String modelo);
	
	public Vehiculo buscarPorPlaca(String placa);

}
