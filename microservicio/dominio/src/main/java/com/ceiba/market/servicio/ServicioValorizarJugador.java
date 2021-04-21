package com.ceiba.market.servicio;

import com.ceiba.dominio.excepcion.ExcepcionFechaValorizacion;
import com.ceiba.dominio.excepcion.ExcepcionNoExiste;
import com.ceiba.dominio.excepcion.ExcepcionTemporadaNoValida;
import com.ceiba.market.modelo.entidad.Jugador;
import com.ceiba.market.puerto.repositorio.RepositorioJugador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ServicioValorizarJugador {

    public static final String NO_EXISTE_JUGADOR = "El jugador no existe";
    public static final String TEMPORADA_FINALIZADA = "No está en el rango de la temporada";
    public static final String NO_VALORIZADO = "No se puede valorizar el jugador en este mes";
    private static final String FORMATO_FECHA =  "yyyy-MM-dd";
    private static final double VALOR_MINUTO_JUGADO =  50000;
    private static final double VALOR_GOL = 2000000;
    private static final double VALOR_TORNEO_GANADO = 100000000;
    private static final double VALOR_MAYOR_EDAD = 4000000;
    private static final double VALOR_MENOR_EDAD = 5000000;

    private final RepositorioJugador repositorioJugador;
    private static final Logger LOGGER = LoggerFactory.getLogger(ServicioValorizarJugador.class);

    public ServicioValorizarJugador(RepositorioJugador repositorioJugador) {
        this.repositorioJugador = repositorioJugador;
    }

    public void ejecutar(Jugador jugador){
        validarExistenciaPrevia(jugador);
        validarTemporada(jugador);
        validarFechaValorizacion(jugador);
        jugador = calcularValorizacion(jugador);
        this.repositorioJugador.actualizar(jugador);
    }

    public void validarExistenciaPrevia(Jugador jugador) {
        boolean existe = this.repositorioJugador.existe(jugador.getNumeroIdentificacion());
        if(!existe)
        {
            LOGGER.error(NO_EXISTE_JUGADOR);
            throw new ExcepcionNoExiste(NO_EXISTE_JUGADOR);
        }
    }

    public void validarTemporada(Jugador jugador) {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern(FORMATO_FECHA);
        String formatoFechaActual = formato.format(fechaActual);
        LocalDate fechaActualFormateada = LocalDate.parse(formatoFechaActual, formato);
        LocalDate fechaInicioTemporada = LocalDate.parse(jugador.getFechaInicioTemporada(), formato);
        LocalDate fechaFinTemporada = LocalDate.parse(jugador.getFechaFinTemporada(), formato);

       if (fechaActualFormateada.isBefore(fechaInicioTemporada) || fechaFinTemporada.isBefore(fechaActualFormateada)) {
           LOGGER.error(TEMPORADA_FINALIZADA);
            throw new ExcepcionTemporadaNoValida(TEMPORADA_FINALIZADA);
        }
    }

    public void validarFechaValorizacion(Jugador jugador){

        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern(FORMATO_FECHA);
        String formatoFechaActual = formato.format(fechaActual);
        LocalDate fechaActualFormateada = LocalDate.parse(formatoFechaActual, formato);
        LocalDate fechaValorizacion = LocalDate.parse(jugador.getFechaValorizacion(), formato);

        if( fechaValorizacion.getMonth().getValue() <= fechaActualFormateada.getMonth().getValue()){
            LOGGER.error(NO_VALORIZADO);
            throw new ExcepcionFechaValorizacion(NO_VALORIZADO);
        }
    }

    public Jugador calcularValorizacion(Jugador jugador){
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern(FORMATO_FECHA);
        String formatoFechaActual = formato.format(fechaActual);
        double valorizacion=0;
        double valorMinutos = jugador.getMinutosJugados() * VALOR_MINUTO_JUGADO;
        double valorGol = jugador.getGoles() * VALOR_GOL;
        double valorTorneos = jugador.getTorneosGanados() * VALOR_TORNEO_GANADO;
        double subValorizacion = valorMinutos + valorGol +valorTorneos;
        double valorizacionActual = Double.parseDouble(jugador.getValorizacion());

        valorizacion = subValorizacion + calcularValorizacionPorEdad(jugador.getEdad()) + valorizacionActual;
        jugador.setFechaValorizacion(formatoFechaActual);
        jugador.setValorizacion(valorizacion+"");

        return jugador;
    }

    public double calcularValorizacionPorEdad(int edad){
        double valorizacionEdad=0;
        if(edad >= 21){
            valorizacionEdad = VALOR_MAYOR_EDAD;
        }else if(edad < 21){
            valorizacionEdad = VALOR_MENOR_EDAD;
        }
        return valorizacionEdad;
    }
}

