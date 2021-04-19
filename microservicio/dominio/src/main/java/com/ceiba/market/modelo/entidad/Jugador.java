package com.ceiba.market.modelo.entidad;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Jugador {

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

    public Jugador(Long idJugador, int numeroIdentificacion, int edad, String valorizacion, Double calificacion,
                   String fechaInicioTemporada, String fechaFinTemporada, String fechaValorizacion, String equipoFutbol,
                   int minutosJugados, int torneosGanados, int goles) {

        this.idJugador = idJugador;
        this.numeroIdentificacion = numeroIdentificacion;
        this.edad = edad;
        this.valorizacion = valorizacion;
        this.calificacion = calificacion;
        this.fechaInicioTemporada = fechaInicioTemporada;
        this.fechaFinTemporada = fechaFinTemporada;
        this.fechaValorizacion = fechaValorizacion;
        this.equipoFutbol = equipoFutbol;
        this.minutosJugados = minutosJugados;
        this.torneosGanados = torneosGanados;
        this.goles = goles;
    }
}
