package com.ceiba.market.servicio.testdatabuilder;

import com.ceiba.market.modelo.dto.DtoJugador;
import com.ceiba.market.modelo.entidad.Historial;
import com.ceiba.market.modelo.entidad.Jugador;

public class HistorialTestDataBuilder {

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

    public HistorialTestDataBuilder(){
         this.idHistorial = 111L;
         this.numeroIdentificacion = 1116745412;
         this.valorTransferencia = "";
         this.fechaTransferencia = "";
         this.fechaCalificacion = "";
         this.equipoFutbolAnterior = "";
         this.equipoFutbolActual = "";
         this.minutosJugados = 160;
         this.torneoGanados = 3;
         this.goles = 3;
    }

    public HistorialTestDataBuilder conIdHistorial(Long idHistorial) {
        this.idHistorial = idHistorial;
        return this;
    }

    public HistorialTestDataBuilder conNumeroIdentificacion(int numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
        return this;
    }

    public HistorialTestDataBuilder conValorTransferencia(String valorTransferencia) {
        this.valorTransferencia = valorTransferencia;
        return this;
    }

    public HistorialTestDataBuilder conFechaTransferencia(String fechaTransferencia) {
        this.fechaTransferencia = fechaTransferencia;
        return this;
    }

    public HistorialTestDataBuilder conFechaCalificacion(String fechaCalificacion) {
        this.fechaCalificacion = fechaCalificacion;
        return this;
    }

    public HistorialTestDataBuilder conEquipoFutbolAnterior(String equipoFutbolAnterior) {
        this.equipoFutbolAnterior = equipoFutbolAnterior;
        return this;
    }

    public HistorialTestDataBuilder conEquipoFutbolActual(String equipoFutbolActual) {
        this.equipoFutbolActual = equipoFutbolActual;
        return this;
    }

    public HistorialTestDataBuilder conMinutosJugados(int minutosJugados) {
        this.minutosJugados = minutosJugados;
        return this;
    }

    public HistorialTestDataBuilder conTorneoGanados(int torneoGanados) {
        this.torneoGanados = torneoGanados;
        return this;
    }

    public HistorialTestDataBuilder conGoles(int goles) {
        this.goles = goles;
        return this;
    }

    public Historial build()
    {
        return new Historial(this.idHistorial, this.numeroIdentificacion, this.valorTransferencia,
                 this.fechaTransferencia, this.fechaCalificacion, this.equipoFutbolAnterior,
                this.equipoFutbolActual, this.minutosJugados,  this.torneoGanados, this.goles);
    }
}
