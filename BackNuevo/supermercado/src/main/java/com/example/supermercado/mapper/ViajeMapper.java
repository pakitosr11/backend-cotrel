package com.example.supermercado.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.supermercado.modelo.dto.ViajeDTO;
import com.example.supermercado.modelo.entity.Viaje;

@Component
public class ViajeMapper {

	@Autowired
	private ConductorMapper conductorMapper;

	public ViajeDTO convertirAViajeDTO(Viaje viaje) {
		ViajeDTO viajeDTO = new ViajeDTO();
		viajeDTO.setId(viaje.getId());
		viajeDTO.setFecha(viaje.getFecha());
		viajeDTO.setOrigen(viaje.getOrigen());
		viajeDTO.setDestino(viaje.getDestino());
		viajeDTO.setMercancia(viaje.getMercancia());
		viajeDTO.setPeso(viaje.getPeso());
		viajeDTO.setPrecio(viaje.getPrecio());
		viajeDTO.setKilometros(viaje.getKilometros());
		if (viaje.getConductor() != null) {
			viajeDTO.setConductor(conductorMapper.convertirAConductorDTO(viaje.getConductor()));
		}
		return viajeDTO;
	}

	public Viaje convertirAViaje(ViajeDTO viajeDTO) {
		Viaje viaje = new Viaje();
		viaje.setId(viajeDTO.getId());
		viaje.setFecha(viajeDTO.getFecha());
		viaje.setOrigen(viajeDTO.getOrigen());
		viaje.setDestino(viajeDTO.getDestino());
		viaje.setMercancia(viajeDTO.getMercancia());
		viaje.setPeso(viajeDTO.getPeso());
		viaje.setPrecio(viajeDTO.getPrecio());
		viaje.setKilometros(viajeDTO.getKilometros());
		if (viajeDTO.getConductor() != null) {
			viaje.setConductor(conductorMapper.convertirAConductor(viajeDTO.getConductor()));
		}
		return viaje;
	}

}
