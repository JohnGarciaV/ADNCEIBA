package com.ceiba.market.servicio;

import com.ceiba.core.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.market.modelo.entidad.Jugador;
import com.ceiba.market.puerto.repositorio.RepositorioJugador;
import com.ceiba.market.servicio.testdatabuilder.JugadorTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class ServicioCrearJugadorTest {
    private static final Long ID_JUGADOR =  111L;

    @Test
    public void crearJugadorTest() throws Exception {
        // arrange
        JugadorTestDataBuilder jugadorTestDataBuilder = new JugadorTestDataBuilder().conIdJugador(ID_JUGADOR);

        RepositorioJugador repositorioJugador = Mockito.mock(RepositorioJugador.class);
        ServicioCrearJugador servicioCrearPago = new ServicioCrearJugador(repositorioJugador);

        //act
        Jugador jugador = jugadorTestDataBuilder.build();
        Mockito.when(repositorioJugador.crear(jugador)).thenReturn(ID_JUGADOR);
        servicioCrearPago.ejecutar(jugador);

        //assert
        assertEquals(jugador.getIdJugador(), ID_JUGADOR);
    }

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
}
