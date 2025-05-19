package com.example.supermercado.controladores;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.example.supermercado.modelo.dto.CamionDTO;
import com.example.supermercado.servicios.CamionService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping({ "/camiones" })
public class ControladorCamion {

	@Autowired
	CamionService camionService;

	@GetMapping
	public ResponseEntity<?> listar() {
		try {
			List<CamionDTO> listaCamiones = this.camionService.obtenerCamiones();
			return ResponseEntity.ok(listaCamiones);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity<?> agregar(@RequestBody CamionDTO c) {
		try {
			CamionDTO camion = this.camionService.add(c);
			return ResponseEntity.ok(camion);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<?> listarId(@PathVariable("id") Long id) {
		try {
			CamionDTO camion = camionService.listarId(id);
			return ResponseEntity.ok(camion);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PutMapping(path = { "/{id}" })
	public ResponseEntity<?> editar(@RequestBody CamionDTO c, @PathVariable("id") Long id) {
		c.setId(id);
		try {
			CamionDTO camionActualizado = camionService.add(c);
			return ResponseEntity.ok(camionActualizado);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {
			CamionDTO camion = this.camionService.delete(id);
			return ResponseEntity.ok(camion);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/fechacomprarango")
	public ResponseEntity<?> getItemsByDateRange(
			@RequestParam(value = "desde", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date desde,
			@RequestParam(value = "hasta", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date hasta) {

		try {
			List<CamionDTO> camiones;
			if (desde != null && hasta != null) {
				// Si tanto desde como hasta están presentes, filtra por rango de fechas
				camiones = camionService.getCamionesByFechaCompraRange(desde, hasta);
				return ResponseEntity.ok(camiones);
			} else if (desde != null) {
				// Si solo desde está presente, filtra registros desde esa fecha
				camiones = camionService.findByFechaCompraAfter(desde);
				return ResponseEntity.ok(camiones);
			} else if (hasta != null) {
				// Si solo hasta está presente, filtra registros hasta esa fecha
				camiones = camionService.findByFechaCompraBefore(hasta);
				return ResponseEntity.ok(camiones);
			} else {
				// Si tanto desde como hasta están ausentes, devuelve todos los registros
				camiones = camionService.obtenerCamiones();
				return ResponseEntity.ok(camiones);
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@GetMapping("/camionesSinItv")
	public ResponseEntity<?> listarCamionesSinITV() {
		try {
			List<CamionDTO> listaCamiones = this.camionService.listaCamionesSinITV();
			return ResponseEntity.ok(listaCamiones);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
