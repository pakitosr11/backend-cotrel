package com.example.supermercado.modelo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "camiones")
public class Camion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column
    private String marca;
    
    @Column
    private String modelo;
    
    @Column
    private String matricula;
    
    @Column
    private String color;
    
    @Column
    private Date fechaCompra;
    
    @Column
    private Date fechaMatriculacion;
    
    @Column
    private Date fechaUltItv;
    
    @OneToOne
    @JoinColumn(name = "conductor_id")
    private Conductor conductor;
    
    
    public Camion() {
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

	public Conductor getConductor() {
		return conductor;
	}

	public void setConductor(Conductor conductor) {
		this.conductor = conductor;
	}

}
