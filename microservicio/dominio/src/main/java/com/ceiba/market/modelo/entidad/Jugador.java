package com.ceiba.market.modelo.entidad;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
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

    public static final String NUMERO_NO_POSITIVO = "Los números deben ser positivos";

    public Jugador(Long idJugador, int numeroIdentificacion, int edad, String valorizacion, Double calificacion,
                   String fechaInicioTemporada, String fechaFinTemporada, String fechaValorizacion, String equipoFutbol,
                   int minutosJugados, int torneosGanados, int goles) {

        validarNumeroPositivo(numeroIdentificacion);
        validarNumeroPositivo(minutosJugados);
        validarNumeroPositivo(torneosGanados);
        validarNumeroPositivo(goles);
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

    private void validarNumeroPositivo(int numero){
        if(numero < 0){
            throw new ExcepcionValorInvalido(NUMERO_NO_POSITIVO);
        }
    }
}
