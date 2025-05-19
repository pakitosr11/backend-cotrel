package com.example.supermercado.modelo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ViajeDTO implements Serializable{
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
    private Date fecha;
	
    private String origen;
    
    private String destino;
    
    private String mercancia;
    
    private BigDecimal peso;
    
    private BigDecimal precio;
    
    private BigDecimal kilometros;
    
    private ConductorDTO conductor;
    
    public ViajeDTO() {
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

	public ConductorDTO getConductor() {
		return conductor;
	}

	public void setConductor(ConductorDTO conductor) {
		this.conductor = conductor;
	}


}
