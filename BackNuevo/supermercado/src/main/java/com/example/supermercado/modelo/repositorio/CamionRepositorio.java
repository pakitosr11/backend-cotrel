package com.example.supermercado.modelo.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.supermercado.modelo.entity.Camion;

public interface CamionRepositorio extends JpaRepository<Camion, Long>{
	
	Camion findByConductorId(Long idConductor);
	
	List<Camion> findByFechaCompraBetween(Date fromDate, Date toDate);

	List<Camion> findByFechaCompraGreaterThanEqual(Date desde);

	List<Camion> findByFechaCompraLessThanEqual(Date hasta);
	
}
