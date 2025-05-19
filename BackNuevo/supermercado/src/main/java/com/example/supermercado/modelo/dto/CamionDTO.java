package com.example.supermercado.modelo.dto;

import java.io.Serializable;
import java.util.Date;


public class CamionDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private String marca;
    private String modelo;
    private String matricula;
    private String color;
    private Date fechaCompra;
    private Date fechaMatriculacion;
    private Date fechaUltItv;
    private ConductorDTO conductor;
    
    public CamionDTO() {
        // Constructor sin argumentos requerido para la deserialización
    }
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Date getFechaCompra() {
		return fechaCompra;
	}
	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	public Date getFechaMatriculacion() {
		return fechaMatriculacion;
	}
	public void setFechaMatriculacion(Date fechaMatriculacion) {
		this.fechaMatriculacion = fechaMatriculacion;
	}
	public Date getFechaUltItv() {
		return fechaUltItv;
	}

	public void setFechaUltItv(Date fechaUltItv) {
		this.fechaUltItv = fechaUltItv;
	}

	public ConductorDTO getConductor() {
		return conductor;
	}
	public void setConductor(ConductorDTO conductor) {
		this.conductor = conductor;
	}
}
