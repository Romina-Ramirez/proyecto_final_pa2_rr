package com.uce.edu.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.ReservaSencillo;
import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.service.IClienteService;
import com.uce.edu.demo.service.IReservaService;
import com.uce.edu.demo.service.IVehiculoService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private IVehiculoService iVehiculoService;

	@Autowired
	private IReservaService iReservaService;

	@Autowired
	private IClienteService iClienteService;

	@GetMapping("/inicio")
	public String mostrarInicioClientes() {

		return "vistaInicioClientes";

	}

	// Funcionalidad 1a
	@GetMapping("/buscar")
	public String buscar(Vehiculo vehiculo) {

		return "vistaBusqueda";
	}

	// Funcionalidad 1a
	@GetMapping("/vehiculosDisponibles")
	public String mostrarVehiculosDisponibles(Vehiculo vehiculo, Model modelo) {
		List<Vehiculo> lista = this.iVehiculoService.buscarVehiculoDisponible(vehiculo.getMarca(),
				vehiculo.getModelo());
		modelo.addAttribute("lista", lista);
		return "vistaVehiculosDisponibles";

	}

	// Funcionalidad 1b
	@GetMapping("/reservar")
	public String reservar(ReservaSencillo reservaSencillo) {
		return "vistaReserva";
	}

	// Funcionalidad 1b
	@GetMapping("/reservarVehiculo")
	public String reservarVehiculo(ReservaSencillo reservaSencillo, Model modelo) {

		Vehiculo vehiculo = this.iVehiculoService.buscarPorPlaca(reservaSencillo.getPlaca());
		modelo.addAttribute("vehiculo", vehiculo);
		modelo.addAttribute("total",
				this.iReservaService.calcularValorTotal(vehiculo.getValorPorDia(),
						LocalDateTime.parse(reservaSencillo.getFechaInicio()),
						LocalDateTime.parse(reservaSencillo.getFechaFin())));
		modelo.addAttribute("reservaSencillo1", reservaSencillo);
		return "vistaReservaVehiculo";
	}

	// Funcionalidad 1b
	@PostMapping("/reservarVehiculoDisponible")
	public String reservarVehiculoDisponible(ReservaSencillo reservaSencillo1, Model modelo) {
		
		String reserva = this.iReservaService.reservarVehiculo(reservaSencillo1.getPlaca(),
				reservaSencillo1.getCedula(), LocalDateTime.parse(reservaSencillo1.getFechaInicio()),
				LocalDateTime.parse(reservaSencillo1.getFechaFin()), reservaSencillo1.getNumeroTarjeta());
		if (reserva.startsWith("No")) {
			modelo.addAttribute("fecha", reserva.subSequence(2, reserva.length()));
			return "vistaReservaFallida";
		}
		modelo.addAttribute("numero", reserva);
		return "vistaReservaExitosa";
	}
	
	//PABLO
	//Funcionalidad 1c
	@GetMapping("/insertarCliente")
	public String registroCliente(Cliente cliente) {

		return "vistaClienteInsertarCliente";
	}
	//Funcionalidad 1c
	@PostMapping("/clientesRegistrados")
	public String insertaCliente(Cliente cliente) {

		cliente.setRegistro("C");
		this.iClienteService.insertarCliente(cliente);

		return "vistaClienteInsertado";
	}
	
	//Funcionalidad 1d
	@GetMapping("/buscarUno/{idCliente}")
	public String buscarEmpleado(@PathVariable("idCliente") Integer id, Model modelo) {

		Cliente clie = this.iClienteService.buscarPorId(id);

		modelo.addAttribute("cliente", clie);

		return "vistaClienteActualizar";
	}
	
	//Funcionalidad 1d
	@PutMapping("/actualizar/{cedulaCliente}")
	public String actualizarCliente(@PathVariable("cedulaCliente") String cedula, Cliente cliente) {

		System.out.println(cedula);
		this.iClienteService.actualizarPorCedula(cedula, cliente.getNombre(), cliente.getApellido(),
				cliente.getFechaNacimiento(), cliente.getGenero());

		return "vistaMensajeActualizar";
	}

	@GetMapping("/regresar")
	public String regresar() {

		return "redirect:/clientes/inicio";

	}

}
