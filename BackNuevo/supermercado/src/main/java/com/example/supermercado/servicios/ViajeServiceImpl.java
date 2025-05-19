package com.example.supermercado.servicios;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.supermercado.mapper.ViajeMapper;
import com.example.supermercado.modelo.dto.ViajeDTO;
import com.example.supermercado.modelo.entity.Viaje;
import com.example.supermercado.modelo.repositorio.ViajeRepositorio;

@Service
public class ViajeServiceImpl implements ViajeService {
	@Autowired
	private ViajeRepositorio viajeRepositorio;

	@Autowired
	private ViajeMapper viajeMapper;

	@Override
	public ViajeDTO listarId(Long id) {
		Viaje viaje = viajeRepositorio.findById(id).orElseThrow(() -> new RuntimeException("Conductor no encontrado"));
		return viajeMapper.convertirAViajeDTO(viaje);
	}

	@Override
	public ViajeDTO add(ViajeDTO v) {
		Viaje viaje = viajeMapper.convertirAViaje(v);
		viaje = viajeRepositorio.save(viaje);

		return viajeMapper.convertirAViajeDTO(viaje);
	}

	@Override
	public ViajeDTO delete(Long id) {
		Optional<Viaje> via = viajeRepositorio.findById(id);
		if (via.isPresent()) {
			this.viajeRepositorio.delete(via.get());
		}
		return viajeMapper.convertirAViajeDTO(via.get());
	}

	public List<ViajeDTO> obtenerViajesPorConductor(Long idConductor) {
		// Implementa la lógica para obtener los viajes para un conductor específico
		// Utiliza el idConductor para filtrar los resultados desde tu repositorio
		// ...

		List<Viaje> viajes = viajeRepositorio.findByConductorId(idConductor);

		return viajes.stream().map(this::convertirAViajeDTO).collect(Collectors.toList());
	}

	public List<ViajeDTO> obtenerTodosLosViajes() {
		List<Viaje> viajes = viajeRepositorio.findAll();
		return viajes.stream().map(this::convertirAViajeDTO).collect(Collectors.toList());
	}

	private ViajeDTO convertirAViajeDTO(Viaje viaje) {
		return viajeMapper.convertirAViajeDTO(viaje);
	}

	// Método para encontrar el viaje con más kilómetros
	public ViajeDTO viajeConMasKilometros(List<ViajeDTO> viajes) {
		ViajeDTO viajeMasLargo = null;
		BigDecimal maxKilometros = BigDecimal.ZERO;

		for (ViajeDTO viaje : viajes) {
			if (viaje.getKilometros() != null && viaje.getKilometros().compareTo(maxKilometros) > 0) {
				maxKilometros = viaje.getKilometros();
				viajeMasLargo = viaje;
			}
		}

		return viajeMasLargo;
	}

	// Método para encontrar el viaje mejor pagado
	public ViajeDTO viajeConMejorPrecio(List<ViajeDTO> viajes) {
		ViajeDTO viajeMejorPrecio = null;
		BigDecimal maxPrecio = BigDecimal.ZERO;

		for (ViajeDTO viaje : viajes) {
			if (viaje.getPrecio() != null && viaje.getPrecio().compareTo(maxPrecio) > 0) {
				maxPrecio = viaje.getPrecio();
				viajeMejorPrecio = viaje;
			}
		}

		return viajeMejorPrecio;
	}

	// Método para encontrar el destino más repetido
	public String destinoMasRepetido(List<ViajeDTO> viajes) {
		Map<String, Integer> conteoDestinos = new HashMap<>();

		// Contar la frecuencia de cada destino
		for (ViajeDTO viaje : viajes) {
			if (viaje.getDestino() != null) {
				String destino = viaje.getDestino();
				conteoDestinos.put(destino, conteoDestinos.getOrDefault(destino, 0) + 1);
			}

		}

		// Encontrar el destino con el mayor conteo
		String destinoMasRepetido = null;
		int maxConteo = 0;
		for (Map.Entry<String, Integer> entry : conteoDestinos.entrySet()) {
			if (entry.getValue() > maxConteo) {
				destinoMasRepetido = entry.getKey();
				maxConteo = entry.getValue();
			}
		}

		return destinoMasRepetido;
	}

	// Método para calcular el precio medio de los viajes
	public BigDecimal precioMedio(List<ViajeDTO> viajes) {
		BigDecimal total = BigDecimal.ZERO;

		// Sumar todos los precios
		for (ViajeDTO viaje : viajes) {
			if (viaje.getPrecio() != null) {
				total = total.add(viaje.getPrecio());
			}
			
		}

		// Calcular el precio medio
		if (!viajes.isEmpty()) {
			return total.divide(BigDecimal.valueOf(viajes.size()), 2, BigDecimal.ROUND_HALF_UP); // 2 decimales
		} else {
			return BigDecimal.ZERO;
		}
	}

}
