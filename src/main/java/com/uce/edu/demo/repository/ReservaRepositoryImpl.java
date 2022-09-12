package com.uce.edu.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.Reserva;

@Repository
@Transactional
public class ReservaRepositoryImpl implements IReservaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void insertar(Reserva reserva) {
		// TODO Auto-generated method stub
		this.entityManager.persist(reserva);
	}

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void actualizar(Reserva reserva) {
		this.entityManager.merge(reserva);
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Reserva> buscarPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		TypedQuery<Reserva> myQuery = this.entityManager.createQuery(
				"SELECT r FROM Reserva r WHERE r.fechaInicio>= :datoFechaInicio AND r.fechaFin<= :datoFechaFin",
				Reserva.class);
		myQuery.setParameter("datoFechaInicio", fechaInicio);
		myQuery.setParameter("datoFechaFin", fechaFin);
		List<Reserva> reservas =myQuery.getResultList();
		for (Reserva reserva : reservas) {
			reserva.getVehiculo().getEstado();
			reserva.getCliente().getFechaNacimiento();
		}
		return reservas;
	}
	
	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Reserva> buscarClientesVip() {
		// TODO Auto-generated method stub
		TypedQuery<Reserva> myQuery = this.entityManager.createQuery("SELECT r FROM Reserva r JOIN r.cliente c ORDER BY r.valorTotal DESC", Reserva.class);
		List<Reserva> reservas=myQuery.getResultList();
		reservas.parallelStream().forEach(r -> r.getCliente().getCedula());
		return reservas;
	}
	
	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Reserva leer(String numero) {
		TypedQuery<Reserva> myQuery = this.entityManager.createQuery("SELECT r FROM Reserva r WHERE r.numero = :numero",
				Reserva.class);
		myQuery.setParameter("numero", numero);
		
		Reserva reserva =myQuery.getSingleResult();
		reserva.getVehiculo().getEstado();
		return reserva;
	}

}
