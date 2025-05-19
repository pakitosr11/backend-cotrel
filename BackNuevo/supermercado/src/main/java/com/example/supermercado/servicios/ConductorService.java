package com.example.supermercado.servicios;

import java.math.BigDecimal;
import java.util.List;

import com.example.supermercado.modelo.dto.ConductorDTO;
import com.example.supermercado.modelo.dto.ConductorNumeroViajesDTO;


public interface ConductorService {

	ConductorDTO listarId(Long id);

	ConductorDTO add(ConductorDTO c) throws Exception;

	ConductorDTO delete(Long id);

	List<ConductorDTO> findAll();
	
	ConductorNumeroViajesDTO obtenerConductorConMasViajes();
	
	ConductorDTO obtenerConductorMejorPagado();
	
	ConductorDTO obtenerConductorMasJoven();
	
	int calcularEdad(ConductorDTO conductor);

	ConductorDTO obtenerConductorMasMayor();

	BigDecimal calcularSueldoMedio();
}
