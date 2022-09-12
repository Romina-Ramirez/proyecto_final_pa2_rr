package com.uce.edu.demo.repository;

import java.util.List;

import com.uce.edu.demo.repository.modelo.Vehiculo;

public interface IVehiculoRepository {

	public List<Vehiculo> buscar(String modelo, String marca);

	public Vehiculo buscarPorPlaca(String placa);

	public List<Vehiculo> buscarVehiculosVip(String fecha);

	public void actualizar(Vehiculo vehiculo);

	public List<Vehiculo> buscarMarca(String marca);

	public Vehiculo buscarPorId(Integer id);

	public void insertarVehiculo(Vehiculo vehiculo);

	public void borrarVehiculo(Integer id);
}
