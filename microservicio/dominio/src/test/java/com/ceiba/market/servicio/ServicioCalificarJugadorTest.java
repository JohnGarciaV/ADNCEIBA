package com.ceiba.market.servicio;

import com.ceiba.core.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionNoExiste;
import com.ceiba.dominio.excepcion.ExcepcionNoExisteDato;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.market.modelo.dto.DtoHistorial;
import com.ceiba.market.modelo.dto.DtoJugador;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ServicioCalificarJugadorTest {

    private static final Long ID_HISTORIAL =  111L;
    private static final String FORMATO_FECHA =  "yyyy-MM-dd";
    private static final String NUMERO_NO_POSITIVO = "Los números deben ser positivos";

    @Test
    public void validarExisteHistorialTest() {
        // arrange
        HistorialTestDataBuilder historialTestDataBuilder =
                new HistorialTestDataBuilder().conIdHistorial(ID_HISTORIAL);
        JugadorTestDataBuilder jugadorTestDataBuilder =
                new JugadorTestDataBuilder().conNumeroIdentificacion(1116587).conMinutosJugados(50)
                        .conTorneosGanados(1).conGoles(3);

        Historial historial = historialTestDataBuilder.build();
        Jugador jugador = jugadorTestDataBuilder.build();
        RepositorioHistorial repositorioHistorial = Mockito.mock(RepositorioHistorial.class);
        DaoHistorial daoHistorial = Mockito.mock(DaoHistorial.class);
        DaoJugador daoJugador = Mockito.mock(DaoJugador.class);
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);
        List<DtoHistorial> listaEsperada = new ArrayList<>();

        //act
        Mockito.when(daoHistorial.listarPorNumeroDocumento(historial.getNumeroIdentificacion())).thenReturn(listaEsperada);
        ServicioCalificarJugador servicioCalificarJugador = new ServicioCalificarJugador(repositorioHistorial,
                daoHistorial, daoJugador, repositorioJugador);

        //assert
        assertEquals(3, servicioCalificarJugador.existeHistorial(historial, jugador).getGoles());
        assertEquals(160, servicioCalificarJugador.existeHistorial(historial, jugador).getMinutosJugados());
    }

    @Test
    public void existeHistorialTest() {
        // arrange
        HistorialTestDataBuilder historialTestDataBuilder =
                new HistorialTestDataBuilder().conIdHistorial(ID_HISTORIAL);
        Historial historial = historialTestDataBuilder.build();

        HistorialTestDataBuilder historialTestDataBuilderDto = new HistorialTestDataBuilder()
                .conNumeroIdentificacion(1116)
                .conGoles(3)
                .conMinutosJugados(50);
        DtoHistorial dtoHistorial = historialTestDataBuilderDto.buildDto();

        JugadorTestDataBuilder jugadorTestDataBuilder =
                new JugadorTestDataBuilder().conNumeroIdentificacion(1116).conMinutosJugados(0)
                        .conTorneosGanados(0).conGoles(0);
        Jugador jugador = jugadorTestDataBuilder.build();
        RepositorioHistorial repositorioHistorial = Mockito.mock(RepositorioHistorial.class);
        DaoHistorial daoHistorial = Mockito.mock(DaoHistorial.class);
        DaoJugador daoJugador = Mockito.mock(DaoJugador.class);
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);

        List<DtoHistorial> listaEsperada = new ArrayList<>();
        listaEsperada.add(dtoHistorial);

        //act
        Mockito.when(daoHistorial.listarPorNumeroDocumento(historial.getNumeroIdentificacion())).thenReturn(listaEsperada);
        ServicioCalificarJugador servicioCalificarJugador = new ServicioCalificarJugador(repositorioHistorial,
                daoHistorial, daoJugador, repositorioJugador);

        //assert
        assertEquals(3, servicioCalificarJugador.existeHistorial(historial, jugador).getGoles());
        assertEquals(50, servicioCalificarJugador.existeHistorial(historial, jugador).getMinutosJugados());
    }

    @Test
    public void validarDiaCalificacionTest() {
        // arrange

        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern(FORMATO_FECHA);
        String formatoFechaActual = formato.format(fechaActual);
        HistorialTestDataBuilder historialTestDataBuilder =
                new HistorialTestDataBuilder().conNumeroIdentificacion(123456)
                        .conFechaCalificacion(formatoFechaActual);
        Historial historial = historialTestDataBuilder.build();
        RepositorioHistorial repositorioHistorial = Mockito.mock(RepositorioHistorial.class);
        DaoHistorial daoHistorial = Mockito.mock(DaoHistorial.class);
        DaoJugador daoJugador = Mockito.mock(DaoJugador.class);
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);

        Mockito.when(repositorioHistorial.existe(historial.getNumeroIdentificacion())).thenReturn(Boolean.TRUE); //act
        ServicioCalificarJugador servicioCalificarJugador = new ServicioCalificarJugador(repositorioHistorial,
                daoHistorial, daoJugador, repositorioJugador);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCalificarJugador.validarDiaCalificacion(historial, historial), ExcepcionNoExisteDato.class,
        servicioCalificarJugador.EXISTE_CALIFICACION);
    }

    @Test
    public void diaCalificacionValidoTest() {
        // arrange
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern(FORMATO_FECHA);
        String formatoFechaActual = formato.format(fechaActual);
        HistorialTestDataBuilder historialTestDataBuilder =
                new HistorialTestDataBuilder().conNumeroIdentificacion(123456)
                        .conFechaCalificacion(formatoFechaActual);
        Historial historial = historialTestDataBuilder.build();

        HistorialTestDataBuilder ultimoHistorialTestDataBuilder =
                new HistorialTestDataBuilder().conNumeroIdentificacion(123456)
                        .conFechaCalificacion("2021-03-16");
        Historial ultimoHistorial = ultimoHistorialTestDataBuilder.build();
        RepositorioHistorial repositorioHistorial = Mockito.mock(RepositorioHistorial.class);
        DaoHistorial daoHistorial = Mockito.mock(DaoHistorial.class);
        DaoJugador daoJugador = Mockito.mock(DaoJugador.class);
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);
        formatoFechaActual = formatoFechaActual+" 00:00:00.0";

      //act
        Mockito.when(repositorioHistorial.existe(historial.getNumeroIdentificacion())).thenReturn(Boolean.TRUE); //act
        ServicioCalificarJugador servicioCalificarJugador = new ServicioCalificarJugador(repositorioHistorial,
                daoHistorial, daoJugador, repositorioJugador);

        assertEquals(formatoFechaActual, servicioCalificarJugador.validarDiaCalificacion(historial, ultimoHistorial).getFechaCalificacion());
    }

    @Test
    public void validarExisteJugadorTest() {
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
    public void ExisteJugadorTest() {
        // arrange
        HistorialTestDataBuilder historialTestDataBuilder =
                new HistorialTestDataBuilder().conNumeroIdentificacion(123456);
        Historial historial = historialTestDataBuilder.build();

        JugadorTestDataBuilder jugador = new JugadorTestDataBuilder()
                                    .conNumeroIdentificacion(123456)
                                    .conGoles(5)
                                    .conMinutosJugados(90)
                                    .conTorneosGanados(1);
        DtoJugador dtoJugador = jugador.buildDto();

        RepositorioHistorial repositorioHistorial = Mockito.mock(RepositorioHistorial.class);
        DaoHistorial daoHistorial = Mockito.mock(DaoHistorial.class);
        DaoJugador daoJugador = Mockito.mock(DaoJugador.class);
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);

        List<DtoJugador> listaEsperada = new ArrayList<>();
        listaEsperada.add(dtoJugador);

        //act
        Mockito.when(daoJugador.listarPorNumeroDocumento(historial.getNumeroIdentificacion())).thenReturn(listaEsperada); //act
        ServicioCalificarJugador servicioCalificarJugador = new ServicioCalificarJugador(repositorioHistorial,
                daoHistorial, daoJugador, repositorioJugador);

        assertEquals(5, servicioCalificarJugador.existeJugador(historial).getGoles());
        assertEquals(90, servicioCalificarJugador.existeJugador(historial).getMinutosJugados());
        assertEquals(1, servicioCalificarJugador.existeJugador(historial).getTorneoGanados());
    }

    @Test
    public void actualizarCalificacionJugadorTest(){
        // arrange
        JugadorTestDataBuilder jugadorTestDataBuilder =
                new JugadorTestDataBuilder()
                        .conNumeroIdentificacion(1116247957)
                        .conMinutosJugados(10);
        HistorialTestDataBuilder historialTestDataBuilder = new HistorialTestDataBuilder()
                .conNumeroIdentificacion(1116247957)
                .conMinutosJugados(10);
        RepositorioHistorial repositorioHistorial = Mockito.mock(RepositorioHistorial.class);
        DaoHistorial daoHistorial = Mockito.mock(DaoHistorial.class);
        DaoJugador daoJugador = Mockito.mock(DaoJugador.class);
        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);
        Historial historial = historialTestDataBuilder.build();
        Jugador jugador = jugadorTestDataBuilder.build();
        Long id =0L;
        ServicioCalificarJugador servicioCalificarJugador = new ServicioCalificarJugador(repositorioHistorial,
                daoHistorial, daoJugador, repositorioJugador);

       /* Mockito.when( servicioCalificarJugador.ejecutar(historial)).thenReturn(id);
        Mockito.when(servicioCalificarJugador.existeHistorial(historial)).thenReturn(historial);

        nuevoHistorial = servicioCalificarJugador.actualizarCalificacionJugador(historial, nuevoHistorial, jugador);

        assertEquals(20, nuevoHistorial.getMinutosJugados());*/
    }

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

        calificacion = servicioCalificarJugador.calcularCalificacion(jugador.getCalificacion(),
                historial.getMinutosJugados(),
                historial.getTorneoGanados(),
                historial.getGoles());

        assertEquals(1.0, calificacion, 0.01);
    }

    @Test
    public void validarNumerosPositivosTest(){
        // arrange
        HistorialTestDataBuilder historialTestDataBuilder =
                new HistorialTestDataBuilder().conMinutosJugados(-1).conTorneoGanados(1).conGoles(3);

        // act - assert
        BasePrueba.assertThrows(() -> historialTestDataBuilder.build(), ExcepcionValorInvalido.class,
                NUMERO_NO_POSITIVO);
    }

    @Test
    public void NumerosPositivosTest(){
        // arrange
        HistorialTestDataBuilder historialTestDataBuilder =
                new HistorialTestDataBuilder().conMinutosJugados(-1).conTorneoGanados(1).conGoles(3);
        // act - assert
        BasePrueba.assertThrows(() -> historialTestDataBuilder.build(), ExcepcionValorInvalido.class,
                NUMERO_NO_POSITIVO);
    }

}
