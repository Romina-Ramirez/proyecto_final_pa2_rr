package com.uce.edu.demo.repository.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "cobro")
public class Cobro {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cobr_id_seq")
	@SequenceGenerator(name = "cobr_id_seq", sequenceName = "cobr_id_seq", allocationSize = 1)
	@Column(name = "cobr_id")
	private Integer id;

	@Column(name = "cobr_valor_subtotal")
	private BigDecimal valorSubtotal;

	@Column(name = "cobr_valor_iva")
	private BigDecimal valorIva;

	@Column(name = "cobr_valor_total")
	private BigDecimal valorTotal;

	@Column(name = "cobr_numero_tarjeta")
	private String numeroTarjeta;
	
	@OneToOne(mappedBy = "cobro")
	private Reserva reserva;

	@Override
	public String toString() {
		return "Cobro [id=" + id + ", valorSubtotal=" + valorSubtotal + ", valorIva=" + valorIva + ", valorTotal="
				+ valorTotal + ", numeroTarjeta=" + numeroTarjeta + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getValorSubtotal() {
		return valorSubtotal;
	}

	public void setValorSubtotal(BigDecimal valorSubtotal) {
		this.valorSubtotal = valorSubtotal;
	}

	public BigDecimal getValorIva() {
		return valorIva;
	}

	public void setValorIva(BigDecimal valorIva) {
		this.valorIva = valorIva;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	
	

}
