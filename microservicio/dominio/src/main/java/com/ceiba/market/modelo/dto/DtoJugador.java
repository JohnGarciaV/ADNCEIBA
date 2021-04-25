package com.ceiba.market.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoJugador {

    private Long idJugador;
    private String nombre;
    private int numeroIdentificacion;
    private int edad;
    private String valorizacion;
    private Double calificacion;
    private String fechaInicioTemporada;
    private String fechaFinTemporada;
    private String fechaValorizacion;
    private String equipoFutbol;
    private int minutosJugados;
    private int torneoGanados;
    private int goles;

}
