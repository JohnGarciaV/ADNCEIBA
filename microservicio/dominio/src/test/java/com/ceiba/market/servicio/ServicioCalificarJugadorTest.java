package com.ceiba.market.servicio;

import com.ceiba.core.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionNoExiste;
import com.ceiba.dominio.excepcion.ExcepcionNoExisteDato;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.market.modelo.entidad.Historial;
import com.ceiba.market.modelo.entidad.Jugador;
import com.ceiba.market.puerto.dao.DaoHistorial;
import com.ceiba.market.puerto.dao.DaoJugador;
import com.ceiba.market.puerto.repositorio.RepositorioHistorial;
import com.ceiba.market.puerto.repositorio.RepositorioJugador;
import com.ceiba.market.servicio.testdatabuilder.HistorialTestDataBuilder;
import com.ceiba.market.servicio.testdatabuilder.JugadorTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ServicioCalificarJugadorTest {

    private static final Long ID_HISTORIAL =  111L;

    @Test
    public void existeHistorialTest() {
        // arrange
        HistorialTestDataBuilder historialTestDataBuilder =
                new HistorialTestDataBuilder().conIdHistorial(ID_HISTORIAL);
        Historial historial = historialTestDataBuilder.build();

        RepositorioHistorial repositorioHistorial = Mockito.mock(RepositorioHistorial.class);
        DaoHistorial daoHistorial = Mockito.mock(DaoHistorial.class);
        DaoJugador daoJugador = Mockito.mock(DaoJugador.class);
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);

        //act
        ServicioCalificarJugador servicioCalificarJugador = new ServicioCalificarJugador(repositorioHistorial,
                daoHistorial, daoJugador, repositorioJugador);

        // act - assert
        BasePrueba.assertThrows(() -> servicioCalificarJugador.existeHistorial(historial), ExcepcionNoExisteDato.class,
                servicioCalificarJugador.NO_EXISTE_HISTORIAL);
    }

    @Test
    public void validarDiaCalificacionTest() {
        // arrange
        HistorialTestDataBuilder historialTestDataBuilder =
                new HistorialTestDataBuilder().conFechaCalificacion("2021-04-17 00:00:00.0");
        Historial historial = historialTestDataBuilder.build();
        RepositorioHistorial repositorioHistorial = Mockito.mock(RepositorioHistorial.class);
        DaoHistorial daoHistorial = Mockito.mock(DaoHistorial.class);
        DaoJugador daoJugador = Mockito.mock(DaoJugador.class);
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);

        Mockito.when(repositorioHistorial.existe(historial.getNumeroIdentificacion())).thenReturn(Boolean.TRUE);
        //act
        ServicioCalificarJugador servicioCalificarJugador = new ServicioCalificarJugador(repositorioHistorial,
                daoHistorial, daoJugador, repositorioJugador);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCalificarJugador.validarDiaCalificacion(historial, historial), ExcepcionNoExisteDato.class,
                servicioCalificarJugador.EXISTE_CALIFICACION);
    }

    @Test
    public void existeJugadorTest() {
        // arrange
        HistorialTestDataBuilder historialTestDataBuilder =
                new HistorialTestDataBuilder().conIdHistorial(ID_HISTORIAL);
        Historial historial = historialTestDataBuilder.build();

        RepositorioHistorial repositorioHistorial = Mockito.mock(RepositorioHistorial.class);
        DaoHistorial daoHistorial = Mockito.mock(DaoHistorial.class);
        DaoJugador daoJugador = Mockito.mock(DaoJugador.class);
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);

        //act
        ServicioCalificarJugador servicioCalificarJugador = new ServicioCalificarJugador(repositorioHistorial,
                daoHistorial, daoJugador, repositorioJugador);

        // act - assert
        BasePrueba.assertThrows(() -> servicioCalificarJugador.existeJugador(historial), ExcepcionNoExiste.class,
                servicioCalificarJugador.NO_EXISTE_JUGADOR);
    }

    @Test
    public void actualizarCalificacionJugadorTest(){
        // arrange
        HistorialTestDataBuilder historialTestDataBuilder = new HistorialTestDataBuilder();
        RepositorioHistorial repositorioHistorial = Mockito.mock(RepositorioHistorial.class);
        DaoHistorial daoHistorial = Mockito.mock(DaoHistorial.class);
        DaoJugador daoJugador = Mockito.mock(DaoJugador.class);
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);
        Historial historial = historialTestDataBuilder.build();
        ServicioCalificarJugador servicioCalificarJugador = new ServicioCalificarJugador(repositorioHistorial,
                daoHistorial, daoJugador, repositorioJugador);

        //actservicioCalificarJugador.ejecutar(historial);

        //assert
     //   Mockito.verify(repositorioHistorial).actualizarCalificacionJugador(pago);
    }

    //TODO:terminar prueba unitaria de actualizar


    @Test
    public void calcularCalificacionTest(){
        // arrange
        HistorialTestDataBuilder historialTestDataBuilder =
                new HistorialTestDataBuilder().conMinutosJugados(90).conTorneoGanados(1).conGoles(3);
        JugadorTestDataBuilder jugadorTestDataBuilder =
                new JugadorTestDataBuilder().conCalificacion(0.0);
        ;       Jugador jugador = jugadorTestDataBuilder.build();
        Historial historial = historialTestDataBuilder.build();
        RepositorioHistorial repositorioHistorial = Mockito.mock(RepositorioHistorial.class);
        DaoHistorial daoHistorial = Mockito.mock(DaoHistorial.class);
        DaoJugador daoJugador = Mockito.mock(DaoJugador.class);
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);
        double calificacion=0.0;

        ServicioCalificarJugador servicioCalificarJugador = new ServicioCalificarJugador(repositorioHistorial,
                daoHistorial, daoJugador, repositorioJugador);

        Mockito.when(servicioCalificarJugador
                .calcularCalificacion(jugador.getCalificacion(), historial.getMinutosJugados(), historial.getTorneoGanados(),
                historial.getGoles()
        )).thenReturn(calificacion);

        assertEquals(2.97, calificacion, 0.01);
    }

    @Test
    public void validarNumerosPositivosTest(){
        // arrange
        HistorialTestDataBuilder historialTestDataBuilder =
                new HistorialTestDataBuilder().conMinutosJugados(-1).conTorneoGanados(1).conGoles(3);
        Historial historial = historialTestDataBuilder.build();
        RepositorioHistorial repositorioHistorial = Mockito.mock(RepositorioHistorial.class);
        DaoHistorial daoHistorial = Mockito.mock(DaoHistorial.class);
        DaoJugador daoJugador = Mockito.mock(DaoJugador.class);
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);
        //act
        ServicioCalificarJugador servicioCalificarJugador = new ServicioCalificarJugador(repositorioHistorial,
                daoHistorial, daoJugador, repositorioJugador);

        // act - assert
        BasePrueba.assertThrows(() -> servicioCalificarJugador.validarNumerosPositivos(historial), ExcepcionValorInvalido.class,
                servicioCalificarJugador.NUMERO_NO_POSITIVO);
    }

}
