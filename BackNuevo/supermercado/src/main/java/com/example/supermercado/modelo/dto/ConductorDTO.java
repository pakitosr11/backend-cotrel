package com.example.supermercado.modelo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class ConductorDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Date fechaNacimiento;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String dni;
	private BigDecimal sueldo;
	private String telefono;
	private List<ViajeDTO> viajes;

	private CamionDTO camion;

	public ConductorDTO() {
		// Constructor sin argumentos requerido para la deserialización
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public BigDecimal getSueldo() {
		return sueldo;
	}

	public void setSueldo(BigDecimal sueldo) {
		this.sueldo = sueldo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<ViajeDTO> getViajes() {
		return viajes;
	}

	public void setViajes(List<ViajeDTO> viajes) {
		this.viajes = viajes;
	}

	public CamionDTO getCamion() {
		return camion;
	}

	public void setCamion(CamionDTO camion) {
		this.camion = camion;
	}



}
