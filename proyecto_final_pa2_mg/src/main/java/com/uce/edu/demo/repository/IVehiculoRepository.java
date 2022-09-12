package com.uce.edu.demo.repository;

import java.util.List;

import com.uce.edu.demo.repository.modelo.Vehiculo;

public interface IVehiculoRepository {
	
	public List<Vehiculo> buscar(String modelo, String marca);
	
	public Vehiculo buscarPorPlaca(String placa);
	
	

}
