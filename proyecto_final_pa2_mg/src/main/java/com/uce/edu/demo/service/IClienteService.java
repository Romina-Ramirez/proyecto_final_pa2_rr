package com.uce.edu.demo.service;

import com.uce.edu.demo.repository.modelo.Cliente;

public interface IClienteService {
	
	public Cliente buscarPorCedula(String cedula);

}
