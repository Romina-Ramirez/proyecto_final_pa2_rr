package com.uce.edu.demo.service;

import java.util.List;

import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.repository.modelo.VehiculoReporte;

public interface IVehiculoService {
	
	public List<Vehiculo> buscarVehiculoDisponible(String marca, String modelo);
	
	public Vehiculo buscarPorPlaca(String placa);
	
	public List<VehiculoReporte> buscarVehiculosVip(String fecha);
	
	public List<Vehiculo> buscarMarca(String marca);

	public Vehiculo buscarPorId(Integer id);

	public void insertarVehiculo(Vehiculo vehiculo);

	public void actualizar(Vehiculo vehiculo);

	public void borrarVehiculo(Integer id);

}
