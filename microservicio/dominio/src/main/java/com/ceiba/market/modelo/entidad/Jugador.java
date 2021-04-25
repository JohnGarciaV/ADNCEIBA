package com.ceiba.market.modelo.entidad;

import com.ceiba.dominio.excepcion.ExcepcionTemporadaNoValida;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class Jugador {

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

    public static final String NUMERO_NO_POSITIVO = "Los números deben ser positivos";
    public static final String FECHA_NO_VALIDA = "Fechas no válidas";

    public Jugador(Long idJugador, String nombre, int numeroIdentificacion, int edad,
                   String valorizacion, Double calificacion, String fechaInicioTemporada,
                   String fechaFinTemporada, String fechaValorizacion, String equipoFutbol,
                   int minutosJugados, int torneoGanados, int goles) {

        validarNumeroPositivo(numeroIdentificacion);
        validarNumeroPositivo(edad);
        validarNumeroPositivo(minutosJugados);
        validarNumeroPositivo(torneoGanados);
        validarNumeroPositivo(goles);
        validarCostoPositivo(valorizacion);
        validarFechasTemporada(fechaInicioTemporada, fechaFinTemporada);
        this.idJugador = idJugador;
        this.nombre = nombre;
        this.numeroIdentificacion = numeroIdentificacion;
        this.edad = edad;
        this.valorizacion = valorizacion;
        this.calificacion = calificacion;
        this.fechaInicioTemporada = fechaInicioTemporada;
        this.fechaFinTemporada = fechaFinTemporada;
        this.fechaValorizacion = fechaValorizacion;
        this.equipoFutbol = equipoFutbol;
        this.minutosJugados = minutosJugados;
        this.torneoGanados = torneoGanados;
        this.goles = goles;
    }

    private void validarNumeroPositivo(int numero){
        if(numero < 0){
            throw new ExcepcionValorInvalido(NUMERO_NO_POSITIVO+ " : "+numero);
        }
    }

    private void validarCostoPositivo(String costo){
        try {
            int valor = Integer.parseInt(costo);
            if (valor < 0) {
                throw new ExcepcionValorInvalido(NUMERO_NO_POSITIVO + " : " + valor);
            }
        }catch (Exception e){
            throw new ExcepcionValorInvalido(NUMERO_NO_POSITIVO + " : " + costo);
        }
    }

    private void validarFechasTemporada(String fechaInicio, String fechaFin){
        try {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fechaInicioDate = LocalDate.parse(fechaInicio, formato);
            LocalDate fechaFinDate = LocalDate.parse(fechaFin, formato);

            if (fechaFinDate.isBefore(fechaInicioDate) || fechaFinDate.isBefore(fechaInicioDate) || fechaInicioDate.isEqual(fechaFinDate)) {
                throw new ExcepcionTemporadaNoValida(FECHA_NO_VALIDA);
            }
        }catch (Exception e){
            throw new ExcepcionTemporadaNoValida(FECHA_NO_VALIDA);
        }
    }
}
