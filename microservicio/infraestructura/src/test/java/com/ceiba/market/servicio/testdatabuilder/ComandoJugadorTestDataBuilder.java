package com.ceiba.market.servicio.testdatabuilder;

import com.ceiba.market.comando.ComandoJugador;

public class ComandoJugadorTestDataBuilder {

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
    private int torneosGanados;
    private int goles;

    public ComandoJugadorTestDataBuilder(){

        this.idJugador = 111L;
        this.numeroIdentificacion = 1116;
        this.nombre = "Pepito Perez";
        this.edad = 18;
        this.valorizacion = "50000000";
        this.calificacion= 2.0;
        this.fechaInicioTemporada= "2021-04-17";
        this.fechaFinTemporada= "2021-09-23";
        this.fechaValorizacion= "2021-04-23";
        this.equipoFutbol="Cortulua";
        this.minutosJugados= 60;
        this.torneosGanados= 1;
        this.goles=2;
    }

    public ComandoJugadorTestDataBuilder conIdJugador(Long id) {
        this.idJugador = idJugador;
        return this;
    }

    public ComandoJugadorTestDataBuilder conNumeroIdentificacion(int numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
        return this;
    }

    public ComandoJugadorTestDataBuilder conEdad(int edad) {
        this.edad = edad;
        return this;
    }

    public ComandoJugadorTestDataBuilder conValorizacion(String valorizacion) {
        this.valorizacion = valorizacion;
        return this;
    }

    public ComandoJugadorTestDataBuilder conCalificacion(Double calificacion) {
        this.calificacion = calificacion;
        return this;
    }

    public ComandoJugadorTestDataBuilder conFechaInicioTemporada(String fechaInicioTemporada) {
        this.fechaInicioTemporada = fechaInicioTemporada;
        return this;
    }

    public ComandoJugadorTestDataBuilder conFechaFinTemporada(String fechaFinTemporada) {
        this.fechaFinTemporada = fechaFinTemporada;
        return this;
    }

    public ComandoJugadorTestDataBuilder conFechaValorizacion(String fechaValorizacion) {
        this.fechaValorizacion = fechaValorizacion;
        return this;
    }

    public ComandoJugadorTestDataBuilder conEquipoFutbol(String equipoFutbol) {
        this.equipoFutbol = equipoFutbol;
        return this;
    }

    public ComandoJugadorTestDataBuilder conMinutosJugados(int minutosJugados) {
        this.minutosJugados = minutosJugados;
        return this;
    }

    public ComandoJugadorTestDataBuilder conTorneosGanados(int torneosGanados) {
        this.torneosGanados = torneosGanados;
        return this;
    }

    public ComandoJugadorTestDataBuilder conGoles(int goles) {
        this.goles = goles;
        return this;
    }

    public ComandoJugador build()
    {
        return new ComandoJugador(this.idJugador,this.nombre,this.numeroIdentificacion,
                this.edad,this.valorizacion, this.calificacion,
                this.fechaInicioTemporada, this.fechaFinTemporada,
                this.fechaValorizacion, this.equipoFutbol,
                this.minutosJugados, this.torneosGanados,
                this.goles);
    }

}
