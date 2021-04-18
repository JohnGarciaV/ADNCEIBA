package com.ceiba.market.servicio.testdatabuilder;

import com.ceiba.market.modelo.entidad.Jugador;

public class JugadorTestDataBuilder {

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

    public JugadorTestDataBuilder(){

        this.idJugador = 111L;
        this.numeroIdentificacion = 1116;
        this.edad = 18;
        this.valorizacion = "50000000.00";
        this.calificacion= 2.0;
        this.fechaInicioTemporada= "2021-04-17";
        this.fechaFinTemporada= "2021-04-23";
        this.fechaValorizacion= "2021-04-23";
        this.equipoFutbol="Cortulua";
        this.minutosJugados= 60;
        this.torneosGanados= 1;
        this.goles=2;
    }

    public JugadorTestDataBuilder conIdJugador(Long id) {
        this.idJugador = idJugador;
        return this;
    }

    public JugadorTestDataBuilder conNumeroIdentificacion(int numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
        return this;
    }

    public JugadorTestDataBuilder conEdad(int edad) {
        this.edad = edad;
        return this;
    }

    public JugadorTestDataBuilder conValorizacion(String valorizacion) {
        this.valorizacion = valorizacion;
        return this;
    }

    public JugadorTestDataBuilder conCalificacion(Double calificacion) {
        this.calificacion = calificacion;
        return this;
    }

    public JugadorTestDataBuilder conFechaInicioTemporada(String fechaInicioTemporada) {
        this.fechaInicioTemporada = fechaInicioTemporada;
        return this;
    }

    public JugadorTestDataBuilder conFechaFinTemporada(String fechaFinTemporada) {
        this.fechaFinTemporada = fechaFinTemporada;
        return this;
    }

    public JugadorTestDataBuilder conFechaValorizacion(String fechaValorizacion) {
        this.fechaValorizacion = fechaValorizacion;
        return this;
    }

    public JugadorTestDataBuilder conEquipoFutbol(String equipoFutbol) {
        this.equipoFutbol = equipoFutbol;
        return this;
    }

    public JugadorTestDataBuilder conMinutosJugados(int minutosJugados) {
        this.minutosJugados = minutosJugados;
        return this;
    }

    public JugadorTestDataBuilder conTorneosGanados(int torneosGanados) {
        this.torneosGanados = torneosGanados;
        return this;
    }

    public JugadorTestDataBuilder conGoles(int goles) {
        this.goles = goles;
        return this;
    }

    public Jugador build()
    {
        return new Jugador(this.idJugador,this.numeroIdentificacion,
                this.edad,this.valorizacion, this.calificacion,
                this.fechaInicioTemporada, this.fechaFinTemporada,
                this.fechaValorizacion, this.equipoFutbol,
                this.minutosJugados, this.torneosGanados,
                this.goles);
    }
}
