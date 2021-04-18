package com.ceiba.market.modelo.entidad;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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

    public Historial(Long idHistorial, int numeroIdentificacion, String valorTransferencia, String fechaTransferencia,
                     String fechaCalificacion, String equipoFutbolAnterior, String equipoFutbolActual,
                     int minutosJugados, int torneoGanados, int goles) {
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
}
