package com.ceiba.dominio.excepcion;

public class ExcepcionValorInvalido extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
    public ExcepcionValorInvalido(String message) {
        super(message);
    }

    public ExcepcionValorInvalido(String message, Exception exception) {
        super(message, exception);
    }
}
