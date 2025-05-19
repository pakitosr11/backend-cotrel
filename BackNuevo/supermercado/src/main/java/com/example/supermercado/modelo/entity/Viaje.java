package com.example.supermercado.modelo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "viajes")
public class Viaje implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column
    private Date fecha;
	
    @Column
    private String origen;
    
    @Column
    private String destino;
    
    @Column
    private String mercancia;
    
    @Column(precision=11, scale=2)
    private BigDecimal peso;
    
    @Column(precision=11, scale=2)
    private BigDecimal precio;
    
    @Column(precision=11, scale=2)
    private BigDecimal kilometros;
    
    @ManyToOne
    @JoinColumn(name = "conductor_id")
    private Conductor conductor;
    
    public Viaje() {
        // Constructor sin argumentos requerido para la deserialización
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getMercancia() {
		return mercancia;
	}

	public void setMercancia(String mercancia) {
		this.mercancia = mercancia;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public BigDecimal getKilometros() {
		return kilometros;
	}

	public void setKilometros(BigDecimal kilometros) {
		this.kilometros = kilometros;
	}

	public Conductor getConductor() {
		return conductor;
	}

	public void setConductor(Conductor conductor) {
		this.conductor = conductor;
	}


}
