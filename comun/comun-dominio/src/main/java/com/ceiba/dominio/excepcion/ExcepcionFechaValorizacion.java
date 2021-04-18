package com.ceiba.dominio.excepcion;

public class ExcepcionFechaValorizacion extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExcepcionFechaValorizacion(String message) {
        super(message);
    }
}
