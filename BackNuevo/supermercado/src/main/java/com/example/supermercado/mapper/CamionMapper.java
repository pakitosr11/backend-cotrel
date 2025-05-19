package com.example.supermercado.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.supermercado.modelo.dto.CamionDTO;
import com.example.supermercado.modelo.dto.ConductorDTO;
import com.example.supermercado.modelo.entity.Camion;
import com.example.supermercado.modelo.entity.Conductor;

@Component
public class CamionMapper{
	
    public CamionDTO convertirACamionDTO(Camion source) {
        if ( source == null ) {
            return null;
        }

        CamionDTO camionDTO = new CamionDTO();

        camionDTO.setId( source.getId() );
        camionDTO.setMarca( source.getMarca() );
        camionDTO.setModelo( source.getModelo() );
        camionDTO.setMatricula( source.getMatricula() );
        camionDTO.setColor( source.getColor() );
        camionDTO.setFechaCompra( source.getFechaCompra() );
        camionDTO.setFechaMatriculacion( source.getFechaMatriculacion() );
        camionDTO.setFechaUltItv( source.getFechaUltItv() );
        camionDTO.setConductor( conductorToConductorDTO( source.getConductor() ) );

        return camionDTO;
    }

    
    public Camion convertirACamion(CamionDTO source) {
        if ( source == null ) {
            return null;
        }

        Camion camion = new Camion();

        camion.setId( source.getId() );
        camion.setMarca( source.getMarca() );
        camion.setModelo( source.getModelo() );
        camion.setMatricula( source.getMatricula() );
        camion.setColor( source.getColor() );
        camion.setFechaCompra( source.getFechaCompra() );
        camion.setFechaMatriculacion( source.getFechaMatriculacion() );
        camion.setFechaUltItv( source.getFechaUltItv() );
        camion.setConductor( conductorDTOToConductor( source.getConductor() ) );

        return camion;
    }

    protected ConductorDTO conductorToConductorDTO(Conductor conductor) {
        if ( conductor == null ) {
            return null;
        }

        ConductorDTO conductorDTO = new ConductorDTO();

        conductorDTO.setId( conductor.getId() );
        conductorDTO.setFechaNacimiento( conductor.getFechaNacimiento() );
        conductorDTO.setNombre( conductor.getNombre() );
        conductorDTO.setApellido1( conductor.getApellido1() );
        conductorDTO.setApellido2( conductor.getApellido2() );
        conductorDTO.setDni( conductor.getDni() );
        conductorDTO.setSueldo( conductor.getSueldo() );
        conductorDTO.setTelefono( conductor.getTelefono() );
        return conductorDTO;
    }

    protected Conductor conductorDTOToConductor(ConductorDTO conductorDTO) {
        if ( conductorDTO == null ) {
            return null;
        }

        Conductor conductor = new Conductor();

        conductor.setId( conductorDTO.getId() );
        conductor.setFechaNacimiento( conductorDTO.getFechaNacimiento() );
        conductor.setNombre( conductorDTO.getNombre() );
        conductor.setApellido1( conductorDTO.getApellido1() );
        conductor.setApellido2( conductorDTO.getApellido2() );
        conductor.setDni( conductorDTO.getDni() );
        conductor.setSueldo( conductorDTO.getSueldo() );
        conductor.setTelefono( conductorDTO.getTelefono() );

        return conductor;
    }
    
    public List<CamionDTO> listEntityToDto(List<Camion> source) {
        if ( source == null ) {
            return null;
        }

        List<CamionDTO> list = new ArrayList<CamionDTO>( source.size() );
        for ( Camion camion : source ) {
            list.add( convertirACamionDTO( camion ) );
        }

        return list;
    }

}
