package com.uce.edu.demo.service;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IClienteRepository;
import com.uce.edu.demo.repository.modelo.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteRepository iClienteRepository;

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Cliente buscarPorCedula(String cedula) {
		return this.iClienteRepository.buscarPorCedula(cedula);
	}
	
	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public void insertarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		this.iClienteRepository.insertarCliente(cliente);
	}

	
	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public void actualizar(Cliente cliente) {
		// TODO Auto-generated method stub
		this.iClienteRepository.actualizar(cliente);
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Cliente buscarPorId(Integer id) {
		return this.iClienteRepository.buscarPorId(id);
	}

	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public void actualizarPorCedula(String cedula, String nombre, String apellido, Date fechaNacimiento,
			String Genero) {
		// TODO Auto-generated method stub
		this.iClienteRepository.actualizarPorCedula(cedula, nombre, apellido, fechaNacimiento, Genero);
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Cliente> buscarPorApellido(String apellido) {
		// TODO Auto-generated method stub
		return this.iClienteRepository.buscarPorApellido(apellido);
	}

	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		this.iClienteRepository.eliminar(id);
	}
	
	
	
	

}
