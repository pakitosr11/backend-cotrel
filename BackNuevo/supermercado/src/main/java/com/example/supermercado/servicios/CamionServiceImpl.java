package com.example.supermercado.servicios;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.supermercado.exception.CotrelException;
import com.example.supermercado.mapper.CamionMapper;
import com.example.supermercado.modelo.dto.CamionDTO;
import com.example.supermercado.modelo.entity.Camion;
import com.example.supermercado.modelo.repositorio.CamionRepositorio;

@Service
public class CamionServiceImpl implements CamionService {
	@Autowired
	private CamionRepositorio camionRepositorio;

	@Autowired
	private CamionMapper camionMapper;

	@Override
	public List<CamionDTO> obtenerCamiones() {
		List<Camion> camiones = this.camionRepositorio.findAll();
		return camionMapper.listEntityToDto(camiones);
	}

	@Override
	public CamionDTO listarId(Long id) {
		Camion camion = camionRepositorio.findById(id).orElseThrow(() -> new RuntimeException("Camión no encontrado"));
		return camionMapper.convertirACamionDTO(camion);
	}

	@Override
	public CamionDTO add(CamionDTO c) {
		if (c.getConductor() != null && c.getConductor().getCamion() != null) {
			Camion camionEntity = camionRepositorio.findByConductorId(c.getConductor().getId());
			if (camionEntity != null && !camionEntity.getId().equals(c.getId())) {
				throw new CotrelException("Ya existe un camión asociado a ese conductor");
			}

		}
		Camion camion = camionMapper.convertirACamion(c);
		camion = camionRepositorio.save(camion);

		return camionMapper.convertirACamionDTO(camion);
	}

	@Override
	public CamionDTO delete(Long id) {
		Optional<Camion> cam = camionRepositorio.findById(id);
		if (cam.isPresent()) {
			this.camionRepositorio.delete(cam.get());
		}
		return camionMapper.convertirACamionDTO(cam.get());
	}

	public List<CamionDTO> getCamionesByFechaCompraRange(Date fromDate, Date toDate) {
		List<Camion> listaCamiones = camionRepositorio.findByFechaCompraBetween(fromDate, toDate);
		return camionMapper.listEntityToDto(listaCamiones);
	}

	@Override
	public List<CamionDTO> findByFechaCompraAfter(Date desde) {
		List<Camion> listaCamiones = camionRepositorio.findByFechaCompraGreaterThanEqual(desde);
		return camionMapper.listEntityToDto(listaCamiones);
	}

	@Override
	public List<CamionDTO> findByFechaCompraBefore(Date hasta) {
		List<Camion> listaCamiones = camionRepositorio.findByFechaCompraLessThanEqual(hasta);
		return camionMapper.listEntityToDto(listaCamiones);
	}

	// Método para verificar si un vehículo necesita pasar la ITV
	public static boolean necesitaITV(LocalDate fechaMatriculacion, LocalDate fechaUltimaITV) {
		int edadVehiculo = LocalDate.now().getYear() - fechaMatriculacion.getYear();

		if (edadVehiculo <= 4) {
			// Exento hasta los 4 años
			return false;
		} else if (edadVehiculo <= 10) {
			// Cada 2 años a partir del 4º año
			return fechaUltimaITV.plusYears(2).isBefore(LocalDate.now());
		} else {
			// Cada año para vehículos de más de 10 años
			return fechaUltimaITV.plusYears(1).isBefore(LocalDate.now());
		}
	}

	@Override
	public List<CamionDTO> listaCamionesSinITV() {
		List<CamionDTO> listaCamionesSinITV = new ArrayList<CamionDTO>();
		List<CamionDTO> listaCamiones = obtenerCamiones();

		for (CamionDTO camion : listaCamiones) {
			if (camion.getFechaMatriculacion() != null && camion.getFechaUltItv() != null) {
				Boolean siNecesitaITV = necesitaITV(convertirFecha(camion.getFechaMatriculacion()), 
						convertirFecha(camion.getFechaUltItv()));
				if(siNecesitaITV) {
					listaCamionesSinITV.add(camion);
				}
			}
		}

		return listaCamionesSinITV;
	}

	public static LocalDate convertirFecha(Date date) {
		// Convertir Date a LocalDate
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

}
