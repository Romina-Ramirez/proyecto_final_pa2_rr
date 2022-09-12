package com.uce.edu.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.service.IReservaService;
import com.uce.edu.demo.service.IVehiculoService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private IVehiculoService iVehiculoService;
	
	@Autowired
	private IReservaService iReservaService;

	@GetMapping("/buscar")
	public String buscar(Vehiculo vehiculo) {

		return "vistaBusqueda";
	}
	
	@GetMapping("/vehiculosDisponibles") 
	public String mostrarVehiculosDisponibles(Vehiculo vehiculo, Model modelo){
		List<Vehiculo> lista = this.iVehiculoService.buscarVehiculoDisponible(vehiculo.getMarca(), vehiculo.getModelo());
		modelo.addAttribute("lista", lista);
		return "vistaVehiculosDisponibles";
		
	}
	
	@GetMapping("/reservar")
	public String reservar(Reserva reserva) {
		
		return "vistaReserva";
	}
	
	@GetMapping("/reservarVehiculo")
	public String reservarVehiculo(Reserva reserva, Model modelo) {
		Vehiculo vehiculo = this.iVehiculoService.buscarPorPlaca(reserva.getVehiculo().getPlaca());
		modelo.addAttribute("vehiculo", vehiculo);
		modelo.addAttribute("total",this.iReservaService.calcularValorTotal(vehiculo.getValorPorDia(), reserva.getFechaInicio(), reserva.getFechaFin()));
		modelo.addAttribute("reserva", reserva);
		return "vistaReservaVehiculo";
	}
	
	@PostMapping("/reservarVehiculoDisponible")
	public String reservarVehiculoDisponible(Reserva reserva){
		
		this.iReservaService.reservarVehiculo(reserva.getVehiculo().getPlaca(), reserva.getCliente().getCedula(), reserva.getFechaInicio(), reserva.getFechaFin(), reserva.getNumeroTarjeta());
		
		return "redirect:/clientes/reservar";
	}
}
