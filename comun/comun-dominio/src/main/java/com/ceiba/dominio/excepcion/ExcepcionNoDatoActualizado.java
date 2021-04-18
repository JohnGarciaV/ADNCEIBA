package com.ceiba.dominio.excepcion;

public class ExcepcionNoDatoActualizado extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExcepcionNoDatoActualizado(String mensaje) {
        super(mensaje);
    }
}
