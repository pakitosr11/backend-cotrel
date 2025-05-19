package com.example.supermercado.servicios;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.supermercado.exception.CotrelException;
import com.example.supermercado.mapper.ConductorMapper;
import com.example.supermercado.modelo.dto.ConductorDTO;
import com.example.supermercado.modelo.dto.ConductorNumeroViajesDTO;
import com.example.supermercado.modelo.entity.Camion;
import com.example.supermercado.modelo.entity.Conductor;
import com.example.supermercado.modelo.repositorio.CamionRepositorio;
import com.example.supermercado.modelo.repositorio.ConductorRepositorio;

@Service
public class ConductorServiceImpl implements ConductorService {
	@Autowired
	private ConductorRepositorio conductorRepositorio;

	@Autowired
	private ConductorMapper conductorMapper;

	@Autowired
	private CamionRepositorio camionRepositorio;

	@Override
	public ConductorDTO listarId(Long id) {
		Conductor conductor = conductorRepositorio.findById(id)
				.orElseThrow(() -> new RuntimeException("Conductor no encontrado"));
		return conductorMapper.convertirAConductorDTO(conductor);
	}

	@Override
	public ConductorDTO add(ConductorDTO c) throws Exception {
		if (c.getId() != null && c.getCamion() != null && c.getCamion().getConductor() != null
				&& !c.getCamion().getConductor().getId().equals(c.getId())) {
			throw new CotrelException("Ya existe un conductor asociado a ese camión");
		}
		Conductor conductor = conductorMapper.convertirAConductor(c);
		Camion camion = conductor.getCamion();
		if (c.getId() == null) {
			conductor = conductorRepositorio.save(conductor);
			if (camion != null) {
				camion.setConductor(conductor);
				camion = camionRepositorio.save(camion);
			}
		} else {
			if (camion != null) {
				camion = camionRepositorio.save(camion);
			} else {
				// SI es camión es nulo, se busca si hay un camión asociado a ese conductor y se
				// le pone el conductor a null
				if (conductor.getId() != null) {
					camion = camionRepositorio.findByConductorId(conductor.getId());
					if (camion != null) {
						camion.setConductor(null);
						camion = camionRepositorio.save(camion);
					}

				}

			}
			conductor = conductorRepositorio.save(conductor);
		}

		return conductorMapper.convertirAConductorDTO(conductor);
	}

	@Override
	public ConductorDTO delete(Long id) {
		Optional<Conductor> cond = conductorRepositorio.findById(id);
		if (cond.isPresent()) {
			if (cond.get().getCamion() != null) {
				throw new CotrelException("Existe un camion asociado a ese conductor");
			}
			this.conductorRepositorio.delete(cond.get());
		}
		return conductorMapper.convertirAConductorDTO(cond.get());
	}

	@Override
	public List<ConductorDTO> findAll() {
		List<Conductor> conductores = conductorRepositorio.findAll();
		return conductores.stream().map(this::convertirAConductorDTO).collect(Collectors.toList());
	}

	private ConductorDTO convertirAConductorDTO(Conductor conductor) {
		return conductorMapper.convertirAConductorDTO(conductor);
	}

	@Override
	public ConductorNumeroViajesDTO obtenerConductorConMasViajes() {
		List<Conductor> conductores = conductorRepositorio.findAll();
		Map<Conductor, Long> viajesPorConductor = conductores.stream()
				.collect(Collectors.toMap(conductor -> conductor, conductor -> (long) conductor.getViajes().size())); // Contar
																														// la
																														// cantidad
																														// de
																														// viajes
																														// por
																														// conductor
		Map.Entry<Conductor, Long> conductorMaxViajes = viajesPorConductor.entrySet().stream()
				.max(Map.Entry.comparingByValue()) // Encontrar al conductor con más viajes
				.orElse(null); // Manejar el caso en que no haya ningún conductor con viajes
		if (conductorMaxViajes != null) {
			return new ConductorNumeroViajesDTO(conductorMapper.convertirAConductorDTO(conductorMaxViajes.getKey()),
					conductorMaxViajes.getValue());
		} else {
			return null;
		}
	}

	@Override
	public ConductorDTO obtenerConductorMejorPagado() {
		List<ConductorDTO> conductores = findAll();
		Map<ConductorDTO, BigDecimal> sueldoPorConductor = conductores.stream()
				.collect(Collectors.toMap(conductor -> conductor,
						conductor -> conductor.getSueldo() != null ? conductor.getSueldo() : BigDecimal.ZERO)); // Obtener
																												// el
																												// sueldo
																												// para
																												// cada
																												// conductor
		Map.Entry<ConductorDTO, BigDecimal> conductorMejorPagado = sueldoPorConductor.entrySet().stream()
				.max(Map.Entry.comparingByValue()) // Encontrar al conductor con el sueldo más alto
				.orElse(null); // Manejar el caso en que no haya ningún conductor
		if (conductorMejorPagado != null) {
			return conductorMejorPagado.getKey();
		} else {
			return null;
		}
	}

	@Override
	public ConductorDTO obtenerConductorMasJoven() {
		List<ConductorDTO> conductores = findAll();
		return conductores.stream().max(Comparator.comparing(ConductorDTO::getFechaNacimiento)) // Encontrar al
																								// conductor con la
																								// fecha de nacimiento
																								// más reciente
				.orElse(null); // Manejar el caso en que no haya ningún conductor
	}

	@Override
	public ConductorDTO obtenerConductorMasMayor() {
		List<ConductorDTO> conductores = findAll();
		return conductores.stream().min(Comparator.comparing(ConductorDTO::getFechaNacimiento)) // Encontrar al
																								// conductor con la
																								// fecha de nacimiento
																								// más reciente
				.orElse(null); // Manejar el caso en que no haya ningún conductor
	}

	@Override
	public int calcularEdad(ConductorDTO conductor) {
		Date fechaNacimiento = conductor.getFechaNacimiento();
		Date fechaActual = new Date();
		LocalDate fechaNacimientoLocal = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate fechaActualLocal = fechaActual.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return Period.between(fechaNacimientoLocal, fechaActualLocal).getYears();
	}

	@Override
	public BigDecimal calcularSueldoMedio() {
		List<ConductorDTO> conductores = findAll();
		BigDecimal sumaSueldos = conductores.stream().map(ConductorDTO::getSueldo) // Obtener todos los sueldos
				.filter(sueldo -> sueldo != null) // Filtrar sueldos no nulos
				.reduce(BigDecimal.ZERO, BigDecimal::add); // Sumar los sueldos

		if (conductores.isEmpty()) {
			return BigDecimal.ZERO; // Manejar el caso de lista vacía
		}

		BigDecimal sueldoMedio = sumaSueldos.divide(BigDecimal.valueOf(conductores.size()), 2, RoundingMode.HALF_UP);
		return sueldoMedio;
	}

}
