package com.uce.edu.demo.repository.modelo;

import java.math.BigDecimal;

public class VehiculoReporte {

	private String placa;

	private String marca;

	private String modelo;

	private BigDecimal avaluo;

	private BigDecimal valorPorDia;

	private BigDecimal subtotal;

	private BigDecimal valorTotal;

	private String fecha;

	public VehiculoReporte() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VehiculoReporte(String placa, String marca, String modelo, String estado, String anioFabricacion,
			String paisFabricacion, String cilindraje, BigDecimal avaluo, BigDecimal valorPorDia, BigDecimal subtotal,
			BigDecimal valorTotal) {
		super();
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.avaluo = avaluo;
		this.valorPorDia = valorPorDia;
		this.subtotal = subtotal;
		this.valorTotal = valorTotal;
	}

	@Override
	public String toString() {
		return "VehiculoReporte [placa=" + placa + ", marca=" + marca + ", modelo=" + modelo + ", avaluo=" + avaluo
				+ ", valorPorDia=" + valorPorDia + ", subtotal=" + subtotal + ", valorTotal=" + valorTotal + "]";
	}

	// SET Y GET
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public BigDecimal getAvaluo() {
		return avaluo;
	}

	public void setAvaluo(BigDecimal avaluo) {
		this.avaluo = avaluo;
	}

	public BigDecimal getValorPorDia() {
		return valorPorDia;
	}

	public void setValorPorDia(BigDecimal valorPorDia) {
		this.valorPorDia = valorPorDia;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

}
