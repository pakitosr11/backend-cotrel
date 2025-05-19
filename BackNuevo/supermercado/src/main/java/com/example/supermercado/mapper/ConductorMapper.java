package com.example.supermercado.mapper;

import org.springframework.stereotype.Component;

import com.example.supermercado.modelo.dto.CamionDTO;
import com.example.supermercado.modelo.dto.ConductorDTO;
import com.example.supermercado.modelo.entity.Camion;
import com.example.supermercado.modelo.entity.Conductor;

@Component
public class ConductorMapper{
	public ConductorDTO convertirAConductorDTO(Conductor conductor) {
		ConductorDTO dto = new ConductorDTO();
		dto.setId(conductor.getId());
		dto.setNombre(conductor.getNombre());
		dto.setApellido1(conductor.getApellido1());
		dto.setApellido2(conductor.getApellido2());
		dto.setFechaNacimiento(conductor.getFechaNacimiento());
		dto.setDni(conductor.getDni());
		dto.setSueldo(conductor.getSueldo());
		dto.setTelefono(conductor.getTelefono());
		if (conductor.getCamion() != null) {
			dto.setCamion(convertirACamionDTO(conductor.getCamion()));
		}
		return dto;
	}

	public CamionDTO convertirACamionDTO(Camion camion) {
		CamionDTO dto = new CamionDTO();
		dto.setId(camion.getId());
		dto.setMarca(camion.getMarca());
		dto.setModelo(camion.getModelo());
		dto.setMatricula(camion.getMatricula());
		dto.setColor(camion.getColor());
		dto.setFechaCompra(camion.getFechaCompra());
		dto.setFechaMatriculacion(camion.getFechaMatriculacion());
		return dto;
	}

	public Conductor convertirAConductor(ConductorDTO conductordto) {
		Conductor entity = new Conductor();
		entity.setId(conductordto.getId());
		entity.setNombre(conductordto.getNombre());
		entity.setApellido1(conductordto.getApellido1());
		entity.setApellido2(conductordto.getApellido2());
		entity.setFechaNacimiento(conductordto.getFechaNacimiento());
		entity.setDni(conductordto.getDni());
		entity.setSueldo(conductordto.getSueldo());
		entity.setTelefono(conductordto.getTelefono());
		if (conductordto.getCamion() != null) {
			entity.setCamion(convertirACamion(conductordto.getCamion(), conductordto));
		}
		return entity;
	}

	public Camion convertirACamion(CamionDTO camionDTO, ConductorDTO conductorDTO) {
		Camion entity = new Camion();
		entity.setId(camionDTO.getId());
		entity.setMarca(camionDTO.getMarca());
		entity.setModelo(camionDTO.getModelo());
		entity.setMatricula(camionDTO.getMatricula());
		entity.setColor(camionDTO.getColor());
		entity.setFechaCompra(camionDTO.getFechaCompra());
		entity.setFechaMatriculacion(camionDTO.getFechaMatriculacion());

		Conductor cond = new Conductor();
		cond.setId(conductorDTO.getId());
		entity.setConductor(cond);

		return entity;
	}

}
