package com.ceiba.dominio.excepcion;

public class ExcepcionTemporadaNoValida extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExcepcionTemporadaNoValida(String message) {
        super(message);
    }

    public ExcepcionTemporadaNoValida(String message, Exception exception) {
        super(message, exception);
    }
}
