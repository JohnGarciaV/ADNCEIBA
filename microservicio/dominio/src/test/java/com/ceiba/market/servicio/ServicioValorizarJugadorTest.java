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

    private static final double VALOR_MAYOR_EDAD = 4000000.0;
    private static final double VALOR_MENOR_EDAD = 5000000.0;

    @Test
    public void validarExistenciaPreviaTest() {
        // arrange
        Jugador jugador = new JugadorTestDataBuilder()
                .conIdJugador(1L)
                .conNumeroIdentificacion(1116247957)
                .conFechaValorizacion("2021-05-28")
                .build();
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);

        //act
        Mockito.when(repositorioJugador.existe(jugador.getNumeroIdentificacion())).thenReturn(Boolean.TRUE);
        ServicioValorizarJugador servicioValorizarJugador = new ServicioValorizarJugador(repositorioJugador);

        // act - assert
        servicioValorizarJugador.ejecutar(jugador);
        // act - assert
        Mockito.verify(repositorioJugador, Mockito.times(1)).existe(jugador.getNumeroIdentificacion()); }

    @Test
    public void NoExisteTest() {
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
    public void temporadaValidaTest() {
        // arrange
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);

        JugadorTestDataBuilder jugadorTestDataBuilder =
                new JugadorTestDataBuilder()
                        .conFechaInicioTemporada("2021-01-28")
                        .conFechaFinTemporada("2021-06-28")
                        .conFechaValorizacion("2021-05-28");
        Jugador jugador = jugadorTestDataBuilder.build();
        when(repositorioJugador.existe(Mockito.anyInt())).thenReturn(true);

        //act
        ServicioValorizarJugador servicioValorizarJugador = new ServicioValorizarJugador(repositorioJugador);
        servicioValorizarJugador.ejecutar(jugador);
        // act - assert
        Mockito.verify(repositorioJugador, Mockito.times(1)).actualizar(jugador);
    }

    @Test
    public void FechaValorizacionaValidaTest() {
        // arrange
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);

        JugadorTestDataBuilder jugadorTestDataBuilder =
                new JugadorTestDataBuilder()
                        .conFechaInicioTemporada("2021-01-28")
                        .conFechaFinTemporada("2021-07-28")
                        .conFechaValorizacion("2021-06-28");
        Jugador jugador = jugadorTestDataBuilder.build();
        when(repositorioJugador.existe(Mockito.anyInt())).thenReturn(true);

        //act
        ServicioValorizarJugador servicioValorizarJugador = new ServicioValorizarJugador(repositorioJugador);
        servicioValorizarJugador.ejecutar(jugador);
        // act - assert
        Mockito.verify(repositorioJugador, Mockito.times(1)).actualizar(jugador);
    }

    @Test
    public void validarFechaValorizacionTest() {
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
        BasePrueba.assertThrows(() -> servicioValorizarJugador.validarFechaValorizacion(jugador),
                                ExcepcionFechaValorizacion.class, servicioValorizarJugador.NO_VALORIZADO);
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
        assertEquals(jugador.getValorizacion(), "2.395E8");
    }

    @Test
    public void calcularValorizacionPorMenorEdadTest() {
        // arrange
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);

        JugadorTestDataBuilder jugadorTestDataBuilder =
                new JugadorTestDataBuilder().conEdad(20);
        ;       Jugador jugador = jugadorTestDataBuilder.build();
        when(repositorioJugador.existe(Mockito.anyInt())).thenReturn(true);

        //act
        double respuesta= 0.0;
        ServicioValorizarJugador servicioValorizarJugador = new ServicioValorizarJugador(repositorioJugador);
        respuesta = servicioValorizarJugador.calcularValorizacionPorEdad(jugador.getEdad());

        // act - assert
        assertEquals(VALOR_MENOR_EDAD, respuesta, 0.01);
    }

    @Test
    public void calcularValorizacionPorMayorEdadTest() {
        // arrange
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);

        JugadorTestDataBuilder jugadorTestDataBuilder =
                new JugadorTestDataBuilder().conEdad(22);
        Jugador jugador = jugadorTestDataBuilder.build();
        when(repositorioJugador.existe(Mockito.anyInt())).thenReturn(true);
        //act
        double respuesta= 0.0;
        ServicioValorizarJugador servicioValorizarJugador = new ServicioValorizarJugador(repositorioJugador);
        respuesta = servicioValorizarJugador.calcularValorizacionPorEdad(jugador.getEdad());

        // act - assert
        assertEquals(VALOR_MAYOR_EDAD, respuesta, 0.01);
    }
}
