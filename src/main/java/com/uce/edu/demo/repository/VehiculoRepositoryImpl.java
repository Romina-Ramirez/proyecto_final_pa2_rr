package com.uce.edu.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.Vehiculo;

@Repository
@Transactional
public class VehiculoRepositoryImpl implements IVehiculoRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(value = TxType.MANDATORY)
	public List<Vehiculo> buscar(String modelo, String marca) {
		TypedQuery<Vehiculo> myQuery = this.entityManager.createQuery(
				"SELECT v FROM Vehiculo v WHERE v.marca= :datoMarca AND v.modelo= :datoModelo", Vehiculo.class);
		myQuery.setParameter("datoMarca", marca);
		myQuery.setParameter("datoModelo", modelo);
		return myQuery.getResultList();
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Vehiculo buscarPorPlaca(String placa) {
		// TODO Auto-generated method stub
		TypedQuery<Vehiculo> myQuery = this.entityManager
				.createQuery("SELECT v FROM Vehiculo v WHERE v.placa= :datoPlaca", Vehiculo.class);
		myQuery.setParameter("datoPlaca", placa);
		Vehiculo vehiculo = myQuery.getSingleResult();
		vehiculo.getReservas().size();
		return vehiculo;
	}

	@Override
	public List<Vehiculo> buscarVehiculosVip(String fecha) {
		TypedQuery<Vehiculo> myQuery = this.entityManager.createQuery(
				"SELECT v FROM Vehiculo v JOIN v.reservas r WHERE concat(r.fechaInicio, '') LIKE :datoFecha AND concat(r.fechaFin, '') LIKE :datoFecha",
				Vehiculo.class);
		myQuery.setParameter("datoFecha", fecha);
		List<Vehiculo> vehiculos = myQuery.getResultList();

		for (Vehiculo vehiculo : vehiculos) {
			vehiculo.getReservas().parallelStream().forEach(r -> r.getFechaFin());
		}

		return vehiculos;
	}

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void actualizar(Vehiculo vehiculo) {
		this.entityManager.merge(vehiculo);
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Vehiculo> buscarMarca(String marca) {
		TypedQuery<Vehiculo> myQuery = this.entityManager
				.createQuery("SELECT v FROM Vehiculo v WHERE v.marca= :datoMarca", Vehiculo.class);
		myQuery.setParameter("datoMarca", marca);
		return myQuery.getResultList();
	}

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void insertarVehiculo(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		this.entityManager.persist(vehiculo);
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Vehiculo buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Vehiculo.class, id);
	}

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void borrarVehiculo(Integer id) {
		// TODO Auto-generated method stub
		Vehiculo vehiculo = this.buscarPorId(id);
		this.entityManager.remove(vehiculo);
	}

}
