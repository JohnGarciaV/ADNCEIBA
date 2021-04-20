package com.ceiba.market.modelo.entidad;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Historial {

    private Long idHistorial;
    private int numeroIdentificacion;
    private String valorTransferencia;
    private String fechaTransferencia;
    private String fechaCalificacion;
    private String equipoFutbolAnterior;
    private String equipoFutbolActual;
    private int minutosJugados;
    private int torneoGanados;
    private int goles;

    public static final String NUMERO_NO_POSITIVO = "Los números deben ser positivos";

    public Historial(Long idHistorial, int numeroIdentificacion, String valorTransferencia, String fechaTransferencia,
                     String fechaCalificacion, String equipoFutbolAnterior, String equipoFutbolActual,
                     int minutosJugados, int torneoGanados, int goles) {

        validarNumeroPositivo(numeroIdentificacion);
        validarNumeroPositivo(minutosJugados);
        validarNumeroPositivo(torneoGanados);
        validarNumeroPositivo(goles);
        this.idHistorial = idHistorial;
        this.numeroIdentificacion = numeroIdentificacion;
        this.valorTransferencia = valorTransferencia;
        this.fechaTransferencia = fechaTransferencia;
        this.fechaCalificacion = fechaCalificacion;
        this.equipoFutbolAnterior = equipoFutbolAnterior;
        this.equipoFutbolActual = equipoFutbolActual;
        this.minutosJugados = minutosJugados;
        this.torneoGanados = torneoGanados;
        this.goles = goles;
    }

    private void validarNumeroPositivo(int numero){
        if(numero < 0){
            throw new ExcepcionValorInvalido(NUMERO_NO_POSITIVO);
        }
    }

}
