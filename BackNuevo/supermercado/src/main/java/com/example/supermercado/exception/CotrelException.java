package com.example.supermercado.exception;

public class CotrelException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CotrelException(String mensaje) {
        super(mensaje);
    }
}