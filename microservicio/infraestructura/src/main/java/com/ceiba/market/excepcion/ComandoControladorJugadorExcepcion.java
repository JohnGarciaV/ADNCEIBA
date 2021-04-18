package com.ceiba.market.excepcion;

public class ComandoControladorJugadorExcepcion extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ComandoControladorJugadorExcepcion(String message, Exception e) {
        super(message,e);
    }
}
