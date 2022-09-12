package com.uce.edu.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.ReservaSencillo;
import com.uce.edu.demo.repository.modelo.VehiculoReporte;
import com.uce.edu.demo.service.IClienteService;
import com.uce.edu.demo.service.IReservaService;
import com.uce.edu.demo.service.IVehiculoService;

@Controller
@RequestMapping("/reportes")
public class ReporteController {
	
	@Autowired
	private IReservaService iReservaService;
	
	@Autowired
	private IClienteService iClienteService;
	
	@Autowired
	private IVehiculoService iVehiculoService;

	@GetMapping("/buscar")
	public String buscarReservas(ReservaSencillo reservaSencillo, VehiculoReporte vehiculoReporte) {

		return "vistaReportes";

	}
	
	@GetMapping("/buscarReservas")
	public String buscarReservas(ReservaSencillo reservaSencillo, Model model) {
		List<Reserva> reservas = this.iReservaService.buscarPorRangoFechas(LocalDateTime.parse(reservaSencillo.getFechaInicio()), LocalDateTime.parse(reservaSencillo.getFechaFin()));
		model.addAttribute("reservas",reservas);
		return "vistaReporteReservas";

	}
	
	@GetMapping("/buscarClientesVip")
	public String buscarReservas(Model model) {
		List<Reserva> reservas = this.iReservaService.buscarClientesVip();
		model.addAttribute("reservas",reservas);
		return "vistaReporteClientesVip";

	}
	
	@GetMapping("/buscarVehiculosVip")
	public String buscarVehiculosVip(VehiculoReporte vehiculoReporte, Model model) {
		String fecha= "%"+vehiculoReporte.getFecha()+"%";
		List<VehiculoReporte> vehiculos = this.iVehiculoService.buscarVehiculosVip(fecha);
		model.addAttribute("vehiculos",vehiculos);
		return "vistaReporteVehiculosVip";

	}
}
