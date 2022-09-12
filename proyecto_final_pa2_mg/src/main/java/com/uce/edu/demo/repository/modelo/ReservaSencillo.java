package com.uce.edu.demo.repository.modelo;

public class ReservaSencillo {

	private String placa;

	private String cedula;

	private String fechaInicio;

	private String fechaFin;

	public ReservaSencillo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReservaSencillo(String placa, String cedula, String fechaInicio, String fechaFin) {
		super();
		this.placa = placa;
		this.cedula = cedula;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

}
