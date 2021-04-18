package com.ceiba.market.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoJugador {

    private Long idJugador;
    private int numeroIdentificacion;
    private int edad;
    private String valorizacion;
    private Double calificacion;
    private String fechaInicioTemporada;
    private String fechaFinTemporada;
    private String fechaValorizacion;
    private String equipoFutbol;
    private int minutosJugados;
    private int torneosGanados;
    private int goles;

    @Override
    public String toString() {
        return "ComandoJugador{" +
                "idJugador=" + idJugador +
                ", numeroIdentificacion=" + numeroIdentificacion +
                ", edad=" + edad +
                ", valorizacion=" + valorizacion +
                ", calificacion=" + calificacion +
                ", fechaInicioTemporada='" + fechaInicioTemporada + '\'' +
                ", fechaFinTemporada='" + fechaFinTemporada + '\'' +
                ", fechaValorizacion='" + fechaValorizacion + '\'' +
                ", equipoFutbol='" + equipoFutbol + '\'' +
                ", minutosJugados=" + minutosJugados +
                ", torneosGanados=" + torneosGanados +
                ", goles=" + goles +
                '}';
    }
}
