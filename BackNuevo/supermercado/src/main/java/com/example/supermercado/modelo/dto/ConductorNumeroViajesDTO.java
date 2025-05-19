package com.example.supermercado.modelo.dto;

public class ConductorNumeroViajesDTO {
	private ConductorDTO conductor;
    private Long numeroViajes;

    public ConductorNumeroViajesDTO(ConductorDTO conductor, Long numeroViajes) {
        this.setConductor(conductor);
        this.setNumeroViajes(numeroViajes);
    }

	public ConductorDTO getConductor() {
		return conductor;
	}

	public void setConductor(ConductorDTO conductor) {
		this.conductor = conductor;
	}

	public Long getNumeroViajes() {
		return numeroViajes;
	}

	public void setNumeroViajes(Long numeroViajes) {
		this.numeroViajes = numeroViajes;
	}

}
