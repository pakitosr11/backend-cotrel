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
import org.springframework.web.bind.annotation.RestController;

import com.example.supermercado.modelo.dto.ConductorDTO;
import com.example.supermercado.servicios.ConductorService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping({ "/conductores" })
public class ControladorConductor {

	@Autowired
	ConductorService conductorService;

	@GetMapping
	public ResponseEntity<?> listar() {
		try {
			List<ConductorDTO> listaConductores = this.conductorService.findAll();
			return ResponseEntity.ok(listaConductores);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity<?> agregar(@RequestBody ConductorDTO c) {
		try {
			ConductorDTO conductor = this.conductorService.add(c);
			return ResponseEntity.ok(conductor);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<?> listarId(@PathVariable("id") Long id) {
		try {
			ConductorDTO conductor = conductorService.listarId(id);
			return ResponseEntity.ok(conductor);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PutMapping(path = { "/{id}" })
	public ResponseEntity<?> editar(@RequestBody ConductorDTO c, @PathVariable("id") Long id) {
		c.setId(id);
		try {
			ConductorDTO conductorActualizado = this.conductorService.add(c);
			return ResponseEntity.ok(conductorActualizado);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {
			ConductorDTO conductor = this.conductorService.delete(id);
			return ResponseEntity.ok(conductor);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@GetMapping(path = { "/calcularEdad/{id}" })
	public ResponseEntity<?> calcularEdad(@PathVariable("id") Long id) {
		try {
			ConductorDTO conductor = conductorService.listarId(id);
			int edad = conductorService.calcularEdad(conductor);
			return ResponseEntity.ok(edad);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
