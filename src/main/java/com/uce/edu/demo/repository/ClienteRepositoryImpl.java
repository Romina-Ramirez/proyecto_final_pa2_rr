package com.uce.edu.demo.repository;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.Cliente;

@Repository
@Transactional
public class ClienteRepositoryImpl implements IClienteRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Cliente buscarPorCedula(String cedula) {
		// TODO Auto-generated method stub
		TypedQuery<Cliente> myQuery = this.entityManager
				.createQuery("SELECT c FROM Cliente c WHERE c.cedula= :datoCedula", Cliente.class);
		myQuery.setParameter("datoCedula", cedula);
		return myQuery.getSingleResult();
	}
	
	@Override
	@Transactional(value = TxType.MANDATORY)
	public void insertarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		this.entityManager.persist(cliente);

	}

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void actualizar(Cliente cliente) {
		// TODO Auto-generated method stub
		this.entityManager.merge(cliente);
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Cliente buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Cliente.class, id);
	}

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void actualizarPorCedula(String cedula, String nombre, String apellido, Date fechaNacimiento,
			String genero) {
		// TODO Auto-generated method stub
		Query myQuery = this.entityManager.createQuery(
				"UPDATE Cliente c SET c.nombre = :datoNombre ,c.apellido = :datoApellido, c.fechaNacimiento = :datoFecha, c.genero = :datoGenero WHERE  c.cedula= :datoCedula");
		myQuery.setParameter("datoCedula", cedula);
		myQuery.setParameter("datoNombre", nombre);
		myQuery.setParameter("datoApellido", apellido);
		myQuery.setParameter("datoFecha", fechaNacimiento);
		myQuery.setParameter("datoGenero", genero);

		myQuery.executeUpdate();
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Cliente> buscarPorApellido(String apellido) {

		TypedQuery<Cliente> myQuery = this.entityManager
				.createQuery("SELECT c FROM Cliente c WHERE c.apellido = :apellido", Cliente.class);
		myQuery.setParameter("apellido", apellido);

		return myQuery.getResultList();
	}

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		
		Cliente cliente = this.buscarPorId(id);
		this.entityManager.remove(cliente);
	}



}
