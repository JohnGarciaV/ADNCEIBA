package com.ceiba.market.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoHistorial {

    private Long idHistorial;
    private int numeroIdentificacion;
    private String valorTransferencia;
    private String fechaTransferencia;
    private String fechaCalificacion;
    private String equipoFutbolAnterior;
    private String equipoFutbolTraspaso;
    private int minutosJugados;
    private int torneoGanados;
    private int goles;

    @Override
    public String toString() {
        return "ComandoHistorial{" +
                "idHistorial=" + idHistorial +
                ", numeroIdentificacion=" + numeroIdentificacion +
                ", valorTransferencia='" + valorTransferencia + '\'' +
                ", fechaTransferencia='" + fechaTransferencia + '\'' +
                ", fechaCalificacion='" + fechaCalificacion + '\'' +
                ", equipoFutbolAnterior='" + equipoFutbolAnterior + '\'' +
                ", equipoFutbolTraspaso='" + equipoFutbolTraspaso + '\'' +
                ", minutosJugados=" + minutosJugados +
                ", torneoGanado=" + torneoGanados +
                ", goles=" + goles +
                '}';
    }
}
