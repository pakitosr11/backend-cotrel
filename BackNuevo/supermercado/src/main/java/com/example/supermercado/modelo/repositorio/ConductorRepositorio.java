package com.example.supermercado.modelo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.supermercado.modelo.entity.Conductor;

public interface ConductorRepositorio extends JpaRepository<Conductor, Long>{
	 	List<Conductor> findAll();

	    @SuppressWarnings("unchecked")
	    Conductor save(Conductor c);

	    void delete(Conductor c);

}
