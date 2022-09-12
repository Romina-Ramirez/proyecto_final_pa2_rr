package com.uce.edu.demo.service;

import java.sql.Date;
import java.util.List;

import com.uce.edu.demo.repository.modelo.Cliente;

public interface IClienteService {

	public Cliente buscarPorCedula(String cedula);

	public void insertarCliente(Cliente cliente);

	public void actualizar(Cliente cliente);

	public Cliente buscarPorId(Integer id);

	public void actualizarPorCedula(String cedula, String nombre, String apellido, Date fechaNacimiento, String Genero);

	public List<Cliente> buscarPorApellido(String apellido);

	public void eliminar(Integer id);

}
