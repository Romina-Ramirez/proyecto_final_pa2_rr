package com.uce.edu.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.uce.edu.demo.repository.modelo.Vehiculo;

@SpringBootTest
class VehiculoRepositoryImplTest {

	@Autowired
	private IVehiculoRepository vehiculoRepository;

	@Test
	@Transactional
	void testActualizar() {
		Vehiculo vehiculo = this.vehiculoRepository.buscarPorPlaca("A15-457");
		vehiculo.setPlaca("PWD-748");

		this.vehiculoRepository.actualizar(vehiculo);

		assertEquals(vehiculo.getPlaca(), this.vehiculoRepository.buscarPorPlaca("PWD-748"),
				"Los datos no son iguales.");
	}

}
