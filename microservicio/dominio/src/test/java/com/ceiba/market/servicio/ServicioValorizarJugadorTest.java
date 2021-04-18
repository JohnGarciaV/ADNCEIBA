package com.ceiba.market.servicio;

import com.ceiba.core.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionFechaValorizacion;
import com.ceiba.dominio.excepcion.ExcepcionTemporadaNoValida;
import com.ceiba.market.modelo.entidad.Jugador;
import com.ceiba.market.puerto.repositorio.RepositorioJugador;
import com.ceiba.market.servicio.testdatabuilder.JugadorTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ServicioValorizarJugadorTest {

    private static final double VALOR_SIN_INCREMENTO = 4000000.00;

    @Test
    public void validarExistenciaPreviaTest() {
        // arrange
        Jugador jugador = new JugadorTestDataBuilder().conIdJugador(1L).build();
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);

        //act
        Mockito.when(repositorioJugador.existe(jugador.getNumeroIdentificacion())).thenReturn(Boolean.TRUE);
        ServicioCrearJugador servicioCrearJugador = new ServicioCrearJugador(repositorioJugador);

        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearJugador.ejecutar(jugador), ExcepcionDuplicidad.class, servicioCrearJugador.EL_JUGADOR_YA_FUE_CREADO);
    }

    @Test
    public void validarTemporadaTest() {
        // arrange
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);

        JugadorTestDataBuilder jugadorTestDataBuilder =
                new JugadorTestDataBuilder()
                        .conFechaInicioTemporada("2020-01-28")
                        .conFechaFinTemporada("2021-01-28")
                        .conFechaValorizacion("2020-04-28");
        Jugador jugador = jugadorTestDataBuilder.build();
        when(repositorioJugador.existe(Mockito.anyInt())).thenReturn(true);

        //act
        ServicioValorizarJugador servicioValorizarJugador = new ServicioValorizarJugador(repositorioJugador);

        // act - assert
        BasePrueba.assertThrows(() -> servicioValorizarJugador.validarTemporada(jugador), ExcepcionTemporadaNoValida.class, servicioValorizarJugador.TEMPORADA_FINALIZADA);
    }

    @Test
    public void validarFechaValorizacionaTest() {
        // arrange
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatoFechaActual = formato.format(fechaActual);
        JugadorTestDataBuilder jugadorTestDataBuilder =
                new JugadorTestDataBuilder()
                        .conFechaInicioTemporada("2021-01-28")
                        .conFechaFinTemporada("2021-07-28")
                        .conFechaValorizacion(formatoFechaActual);
        Jugador jugador = jugadorTestDataBuilder.build();
        when(repositorioJugador.existe(Mockito.anyInt())).thenReturn(true);

        //act
        ServicioValorizarJugador servicioValorizarJugador = new ServicioValorizarJugador(repositorioJugador);

        // act - assert
        BasePrueba.assertThrows(() -> servicioValorizarJugador.validarFechaValorizacion(jugador), ExcepcionFechaValorizacion.class, servicioValorizarJugador.NO_VALORIZADO);
    }

    @Test
    public void calcularValorizacionTest() {
        // arrange
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);

        JugadorTestDataBuilder jugadorTestDataBuilder =
                new JugadorTestDataBuilder().
                        conEdad(21).conMinutosJugados(500).conTorneosGanados(2).conGoles(5).
                conValorizacion("500000.00");
;       Jugador jugador = jugadorTestDataBuilder.build();
        when(repositorioJugador.existe(Mockito.anyInt())).thenReturn(true);

        //act
        ServicioValorizarJugador servicioValorizarJugador = new ServicioValorizarJugador(repositorioJugador);

        servicioValorizarJugador.calcularValorizacion(jugador);
        // act - assert
        assertEquals(jugador.getValorizacion(), 2.395E8);
    }

    @Test
    public void calcularValorizacionPorEdadTest() {
        // arrange
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);

        JugadorTestDataBuilder jugadorTestDataBuilder =
                new JugadorTestDataBuilder().conEdad(21);
        ;       Jugador jugador = jugadorTestDataBuilder.build();
        when(repositorioJugador.existe(Mockito.anyInt())).thenReturn(true);

        //act
        double respuesta= 0.0;
        ServicioValorizarJugador servicioValorizarJugador = new ServicioValorizarJugador(repositorioJugador);

        servicioValorizarJugador.calcularValorizacionPorEdad(jugador.getEdad());

        Mockito.when(servicioValorizarJugador.calcularValorizacionPorEdad(jugador.getEdad())).thenReturn(respuesta);
        // act - assert
        //TODO:preguntar como hacerla
        //assertEquals(VALOR_SIN_INCREMENTO, respuesta);
    }
}