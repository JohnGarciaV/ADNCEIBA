package com.ceiba.market.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.market.comando.ComandoHistorial;
import com.ceiba.market.comando.ComandoJugador;
import com.ceiba.market.servicio.testdatabuilder.ComandoHistorialTestDataBuilder;
import com.ceiba.market.servicio.testdatabuilder.ComandoJugadorTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= ApplicationMock.class)
@WebMvcTest(ComandoControladorJugador.class)
public class ComandoControladorJugadorTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    public void crear() throws Exception{
        // arrange
        ComandoJugador jugador = new ComandoJugadorTestDataBuilder().conNumeroIdentificacion(1116123).build();

        // act - assert
        mocMvc.perform(post("/jugador")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(jugador)))
                .andExpect(status().isOk());
    }

    @Test
    public void valorizar() throws Exception{
        // arrange
        ComandoJugador jugador = new ComandoJugadorTestDataBuilder().conNumeroIdentificacion(1116745412).
                conFechaValorizacion("2021-07-13")
                .conFechaInicioTemporada("2021-01-21")
                .conFechaFinTemporada("2021-07-21").build();

        // act - assert
        mocMvc.perform(put("/jugador/valorar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(jugador)))
                .andExpect(status().isOk());
    }

    @Test
    public void calificar() throws Exception{
        // arrange
        ComandoHistorial historial = new ComandoHistorialTestDataBuilder().build();

        // act - assert
        mocMvc.perform(post("/jugador/calificar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(historial)))
                .andExpect(status().isOk());
    }
}
