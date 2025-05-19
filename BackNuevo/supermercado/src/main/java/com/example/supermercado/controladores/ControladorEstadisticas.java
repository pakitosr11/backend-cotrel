package com.example.supermercado.controladores;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.supermercado.modelo.dto.ConductorDTO;
import com.example.supermercado.modelo.dto.ConductorNumeroViajesDTO;
import com.example.supermercado.modelo.dto.ViajeDTO;
import com.example.supermercado.servicios.ConductorService;
import com.example.supermercado.servicios.ViajeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping({ "/estadisticas" })
public class ControladorEstadisticas {

	@Autowired
	ViajeService viajeService;

	@Autowired
	ConductorService conductorService;

	@GetMapping("/viajeMasKM")
	public ResponseEntity<?> viajeConMasKilometros() {
		try {

			// Obtener todos los viajes
			List<ViajeDTO> listaViajes = viajeService.obtenerTodosLosViajes();
			ViajeDTO viaje = viajeService.viajeConMasKilometros(listaViajes);
			return ResponseEntity.ok(viaje);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/viajeMejorPrecio")
	public ResponseEntity<?> viajeConMejorPrecio() {
		try {

			// Obtener todos los viajes
			List<ViajeDTO> listaViajes = viajeService.obtenerTodosLosViajes();
			ViajeDTO viaje = viajeService.viajeConMejorPrecio(listaViajes);
			return ResponseEntity.ok(viaje);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/destinoMasRepetido")
	public ResponseEntity<?> destinoMasRepetido() {
		try {

			// Obtener todos los viajes
			List<ViajeDTO> listaViajes = viajeService.obtenerTodosLosViajes();
			String destino = viajeService.destinoMasRepetido(listaViajes);
			return ResponseEntity.ok(destino);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/precioMedio")
	public ResponseEntity<?> precioMedio() {
		try {

			// Obtener todos los viajes
			List<ViajeDTO> listaViajes = viajeService.obtenerTodosLosViajes();
			BigDecimal precioMedio = viajeService.precioMedio(listaViajes);
			return ResponseEntity.ok(precioMedio);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/obtenerConductorConMasViajes")
	public ResponseEntity<?> obtenerConductorConMasViajes() {
		try {
			ConductorNumeroViajesDTO conductor = conductorService.obtenerConductorConMasViajes();
			return ResponseEntity.ok(conductor);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/obtenerConductorMejorPagado")
	public ResponseEntity<?> obtenerConductorMejorPagado() {
		try {
			ConductorDTO conductor = conductorService.obtenerConductorMejorPagado();
			return ResponseEntity.ok(conductor);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/obtenerConductorMasJoven")
	public ResponseEntity<?> obtenerConductorMasJoven() {
		try {
			ConductorDTO conductor = conductorService.obtenerConductorMasJoven();
			return ResponseEntity.ok(conductor);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/obtenerConductorMasMayor")
	public ResponseEntity<?> obtenerConductorMasMayor() {
		try {
			ConductorDTO conductor = conductorService.obtenerConductorMasMayor();
			return ResponseEntity.ok(conductor);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/calcularSueldoMedio")
	public ResponseEntity<?> calcularSueldoMedio() {
		try {

			BigDecimal sueldoMedio = conductorService.calcularSueldoMedio();
			return ResponseEntity.ok(sueldoMedio);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
