package com.uce.edu.demo.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/empleados")
public class EmpleadoController {

	@Autowired
	private IClienteService iClienteService;

	@Autowired
	private IVehiculoService iVehiculoService;

	@Autowired
	private IReservaService reservaService;

	@GetMapping("/inicio")
	public String elegirOpcion() {
		return "vistaInicio";

	}

	// Funcionalidad 2c
	// INSERTAR VEHICULO
	@GetMapping("/insertarVehiculo")
	public String registroVehiculo(Vehiculo vehiculo) {

		return "vistaInsertarVehiculo";
	}

	// Funcionalidad 2c
	@PostMapping("/vehiculoInsertado")
	public String insertaVehiculo(Vehiculo vehiculo) {
		this.iVehiculoService.insertarVehiculo(vehiculo);

		return "vistaEmpleadoMensajeVehiculoInsertado";

	}

	// Funcionalidad 2d
	// BUSCAR POR MARCA
	@GetMapping("/insertarMarca")
	public String escribirMarca(Vehiculo vehiculo) {

		return "vistaEmpleadoMarca";
	}

	// Funcionalidad 2d
	@GetMapping("/buscarMarca")
	public String buscarMarca(Vehiculo vehiculo, Model modelo) {

		List<Vehiculo> lista = this.iVehiculoService.buscarMarca(vehiculo.getMarca());
		modelo.addAttribute("vehiculos", lista);
		return "vistaEmpleadoListaVehiculo";
	}

	// Funcionalidad 2d
	// BUSCAR POR ID
	@GetMapping("/buscarUnoVehiculo/{idVehiculo}")
	public String buscarVehiculo(@PathVariable("idVehiculo") Integer id, Model modelo) {

		Vehiculo vehiculo = this.iVehiculoService.buscarPorId(id);

		modelo.addAttribute("vehiculo", vehiculo);

		return "vistaEmpleadoVisualizarVehiculo";
	}

	// Funcionalidad 2d
	@GetMapping("/buscarVehiculo/{idVehiculo}")
	public String buscarEmpleadoVehiculoActualizable(@PathVariable("idVehiculo") Integer id, Model modelo) {

		Vehiculo vehiculo = this.iVehiculoService.buscarPorId(id);

		modelo.addAttribute("vehiculo", vehiculo);

		return "vistaEmpleadoActualizarVehiculo";

	}

	// Funcionalidad 2d
	// ACTUALIZAR VEHICULO
	@PutMapping("/actualizarVehiculo")
	public String actualizar(Vehiculo vehiculo) {
		this.iVehiculoService.actualizar(vehiculo);
		return "vistaEmpleadoMensajeVehiculoActualizado";

	}

	// Funcionalidad 2d
	// BORRAR VEHICULO
	@DeleteMapping("/borrarVehiculo/{idVehiculo}")
	public String borrarVehiculo(@PathVariable("idVehiculo") Integer id) {
		this.iVehiculoService.borrarVehiculo(id);
		return "vistaEmpleadoMensajeVehiculoBorrado";
	}

	// Funcionalidad 2a
	@GetMapping("/insertarCliente")
	public String registroCliente(Cliente cliente) {

		return "vistaEmpleadoInsertarCliente";
	}

	// Funcionalidad 2a
	@PostMapping("/clientesRegistrados")
	public String insertaCliente(Cliente cliente) {

		cliente.setRegistro("E");
		this.iClienteService.insertarCliente(cliente);

		return "vistaEmpleadoMensajeClienteInsertado";
	}

	// Funcionalidad 2b
	@GetMapping("/insertarApellido")
	public String escribirApellido(Cliente cliente) {

		return "vistaEmpleadoApellido";
	}

	// Funcionalidad 2b
	@GetMapping("/buscarApellido")
	public String buscarApellido(Cliente cliente, Model modelo) {

		List<Cliente> lista = this.iClienteService.buscarPorApellido(cliente.getApellido());
		modelo.addAttribute("clientes", lista);
		return "vistaEmpleadoListaCliente";
	}

	// Funcionalidad 2b
	@GetMapping("/buscarUno/{idCliente}")
	public String buscarEmpleado(@PathVariable("idCliente") Integer id, Model modelo) {

		Cliente clie = this.iClienteService.buscarPorId(id);

		modelo.addAttribute("cliente", clie);

		return "vistaEmpleadoVisualizarCliente";
	}

	// Funcionalidad 2b
	@GetMapping("/buscarCliente/{idCliente}")
	public String buscarEmpleadoActualizable(@PathVariable("idCliente") Integer id, Model modelo) {

		Cliente clie = this.iClienteService.buscarPorId(id);

		modelo.addAttribute("cliente", clie);

		return "vistaEmpleadoActualizarCliente";
	}

	// Funcionalidad 2b
	@PutMapping("/actualizar")
	public String actualizar(Cliente cliente) {
		this.iClienteService.actualizar(cliente);
		return "vistaEmpleadoMensajeClienteActualizado";

	}

	// Funcionalidad 2b
	@DeleteMapping("/borrar/{idCliente}")
	public String borrarCliente(@PathVariable("idCliente") Integer id) {
		this.iClienteService.eliminar(id);
		return "vistaEmpleadoMensajeClienteBorrado";
	}

	@GetMapping("/regresar")
	public String regresar() {

		return "redirect:/empleados/inicio";

	}

	// ROMI
	// Funcionalidad 2e
	@GetMapping("/numeroReserva")
	public String buscarNumero(Reserva r) {
		return "vistaBusquedaReserva";
	}

	// Funcionalidad 2e
	@GetMapping("/numeroNuevaReserva")
	public String buscarNumeroNuevo(Reserva r) {
		return "vistaNuevaBusquedaReserva";
	}

	// Funcionalidad 2e
	@GetMapping("/retirar")
	public String retirarVehiculo(Reserva r, Model modelo) {
		Reserva reserva = this.reservaService.buscar(r.getNumero());
		if (reserva.getEstado().equals("G")) {
			LocalDateTime fI = reserva.getFechaInicio();
			LocalDateTime fF = reserva.getFechaFin();
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			String fecha = fI.format(dateTimeFormatter) + " - " + fF.format(dateTimeFormatter);
			modelo.addAttribute("reserva", reserva);
			modelo.addAttribute("fecha", fecha);
			return "vistaRetiroVehiculo";
		} else {
			return "redirect:/empleados/numeroNuevaReserva";
		}
	}

	// Funcionalidad 2e
	@PutMapping("/registrarRetiro/{numero}")
	public String registrarRetiro(@PathVariable("numero") String numero, Reserva reserva) {
		this.reservaService.retirarVehiculo(numero);
		return "vistaRetiroExitoso";
	}

	// Funcionalidad 2f
	@GetMapping("/retirarSinReserva")
	public String retirarVehiculoSinReserva(Vehiculo vehiculo, ReservaSencillo reservaSencillo,
			Model model) {
		List<Vehiculo> vehiculos = this.iVehiculoService.buscarVehiculoDisponible(vehiculo.getMarca(),
				vehiculo.getModelo());
		if (vehiculos.size() != 0) {
			model.addAttribute("vehiculos", vehiculos);
		}

		if (reservaSencillo.getPlaca() != null) {
			Vehiculo vehiculo1 = this.iVehiculoService.buscarPorPlaca(reservaSencillo.getPlaca());
			System.out.println(reservaSencillo.getFechaInicio());
			System.out.println(reservaSencillo.getFechaFin());
			if (vehiculo1 != null) {
				model.addAttribute("vehiculo1", vehiculo1);
				model.addAttribute("total",
						this.reservaService.calcularValorTotal(vehiculo1.getValorPorDia(),
								LocalDateTime.parse(reservaSencillo.getFechaInicio()),
								LocalDateTime.parse(reservaSencillo.getFechaFin())));
				model.addAttribute("reservaSencillo1", reservaSencillo);
			}
		} else {
			Vehiculo vehi = new Vehiculo();
			vehi.setPlaca("");
			vehi.setEstado("");
			vehi.setValorPorDia(BigDecimal.ZERO);
			model.addAttribute("vehiculo1", vehi);
			model.addAttribute("total", "0.0");
			model.addAttribute("reservaSencillo1", reservaSencillo);
		}
		return "vistaEmpleadoNoReserva";
	}
	
	@PostMapping("/reservarVehiculoDisponible")
	public String reservarVehiculoDisponible(ReservaSencillo reservaSencillo1, Model modelo) {
		System.out.println(reservaSencillo1.getFechaInicio());
		System.out.println(reservaSencillo1.getFechaFin());
		System.out.println(reservaSencillo1.getNumeroTarjeta());
		String reserva = this.reservaService.reservarVehiculo(reservaSencillo1.getPlaca(),
				reservaSencillo1.getCedula(), LocalDateTime.parse(reservaSencillo1.getFechaInicio()),
				LocalDateTime.parse(reservaSencillo1.getFechaFin()), reservaSencillo1.getNumeroTarjeta());
		if (reserva.startsWith("No")) {
			modelo.addAttribute("fecha", reserva.subSequence(2, reserva.length()));
			return "vistaEmpleadoReservaFallida";
		}
		modelo.addAttribute("numero", reserva);
		return "vistaEmpleadoReservaExitosa";
	}
	
	@PutMapping("/registrarRetiroSinReserva/{numero}")
	public String registrarRetiroSinReserva(@PathVariable("numero") String numero, Reserva reserva) {
		this.reservaService.retirarVehiculo(numero);
		return "vistaRetiroExitoso";
	}

}
