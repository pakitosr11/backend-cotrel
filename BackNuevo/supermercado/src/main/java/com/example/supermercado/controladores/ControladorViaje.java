package com.example.supermercado.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.supermercado.modelo.dto.ViajeDTO;
import com.example.supermercado.servicios.ViajeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping({ "/viajes" })
public class ControladorViaje {

	@Autowired
	ViajeService viajeService;

	@PostMapping
	public ResponseEntity<?> agregar(@RequestBody ViajeDTO v) {
		try {
			ViajeDTO viaje = this.viajeService.add(v);
			return ResponseEntity.ok(viaje);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<?> listarId(@PathVariable("id") Long id) {
		try {
			ViajeDTO viaje = viajeService.listarId(id);
			return ResponseEntity.ok(viaje);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PutMapping(path = { "/{id}" })
	public ResponseEntity<?> editar(@RequestBody ViajeDTO v, @PathVariable("id") Long id) {
		v.setId(id);
		try {
			ViajeDTO viajeActualizado = this.viajeService.add(v);
			return ResponseEntity.ok(viajeActualizado);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {
			ViajeDTO viaje = this.viajeService.delete(id);
			return ResponseEntity.ok(viaje);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping
	public ResponseEntity<?> obtenerViajes(@RequestParam(required = false) Long idConductor) {
		try {
			if (idConductor != null) {
				// Obtener viajes para un conductor específico
				List<ViajeDTO> listaViajes = viajeService.obtenerViajesPorConductor(idConductor);
				return ResponseEntity.ok(listaViajes);
			} else {
				// Obtener todos los viajes
				List<ViajeDTO> listaViajes = viajeService.obtenerTodosLosViajes();
				return ResponseEntity.ok(listaViajes);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
