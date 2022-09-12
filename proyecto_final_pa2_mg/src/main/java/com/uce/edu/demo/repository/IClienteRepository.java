package com.uce.edu.demo.repository;

import com.uce.edu.demo.repository.modelo.Cliente;

public interface IClienteRepository {
	
	public Cliente buscarPorCedula(String cedula);

}
