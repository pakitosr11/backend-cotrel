package com.example.supermercado.modelo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.supermercado.modelo.entity.Viaje;

public interface ViajeRepositorio extends JpaRepository<Viaje, Long>{
	 	List<Viaje> findAll();

	    @SuppressWarnings("unchecked")
	    Viaje save(Viaje v);

	    void delete(Viaje v);

		List<Viaje> findByConductorId(Long idConductor);

}
