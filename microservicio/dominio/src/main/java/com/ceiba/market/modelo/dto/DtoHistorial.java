package com.ceiba.market.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoHistorial {
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

}
