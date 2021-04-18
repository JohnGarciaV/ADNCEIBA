package com.ceiba.dominio.excepcion;

public class ExcepcionNoExisteDato extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExcepcionNoExisteDato(String message) {
        super(message);
    }
}
