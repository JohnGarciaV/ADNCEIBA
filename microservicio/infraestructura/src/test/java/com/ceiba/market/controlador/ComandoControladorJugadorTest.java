package com.ceiba.market.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.market.comando.ComandoHistorial;
import com.ceiba.market.comando.ComandoJugador;
import com.ceiba.market.servicio.testdatabuilder.ComandoHistorialTestDataBuilder;
import com.ceiba.market.servicio.testdatabuilder.ComandoJugadorTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= ApplicationMock.class)
@WebMvcTest(ComandoControladorJugador.class)
public class ComandoControladorJugadorTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    private static final String FORMATO_FECHA =  "yyyy-MM-dd";

    @Test
    public void crear() throws Exception{
        // arrange
        ComandoJugador jugador = new ComandoJugadorTestDataBuilder().conNumeroIdentificacion(1116123).build();
        // act - assert
        mocMvc.perform(post("/jugador")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(jugador)))
                .andExpect(status().isOk())
                .andDo(
                    result -> mocMvc.perform(get("/jugador")
                            .param("id", String.valueOf(jugador.getNumeroIdentificacion())))
                            . andExpect(status().isOk())
                );
    }

    @Test
    public void valorizar() throws Exception{
        // arrange
        ComandoJugador jugador = new ComandoJugadorTestDataBuilder().conNumeroIdentificacion(1116745412).
                conFechaValorizacion("2021-07-13")
                .conFechaInicioTemporada("2021-01-21")
                .conFechaFinTemporada("2021-07-21")
                .conValorizacion("500000.00")
                .conEdad(21)
                .conMinutosJugados(100)
                .conTorneosGanados(2)
                .conGoles(5)
                .build();

        // act - assert
        mocMvc.perform(put("/jugador/valorar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(jugador)))
                .andExpect(status().isOk())
                .andDo(
                        resultValorar -> {
                        MvcResult requestResultJugador = mocMvc.perform(get("/jugador")
                                .param("id", "1116745412"))
                                .andExpect(status().isOk())
                                .andReturn();
                        String contentAsString = requestResultJugador.getResponse().getContentAsString();
                        contentAsString = contentAsString.replace("[", "");
                        contentAsString = contentAsString.replace("]", "");
                        JSONObject updateJugadorResponse = new JSONObject(contentAsString);
                        LocalDate fechaActual = LocalDate.now();
                        DateTimeFormatter formato = DateTimeFormatter.ofPattern(FORMATO_FECHA);
                        String formatoFechaActual = formato.format(fechaActual);
                        assertEquals(1116745412, updateJugadorResponse.getInt("numeroIdentificacion"));
                        assertEquals("200000000", updateJugadorResponse.getInt("valorizacion"));
                        assertEquals(formatoFechaActual, updateJugadorResponse.getInt("fechaValorizacion"));
                        }
                );
    }

    @Test
    public void calificar() throws Exception{
        // arrange
        ComandoHistorial historial = new ComandoHistorialTestDataBuilder()
                .conNumeroIdentificacion(1116745412)
                .conMinutosJugados(90)
                .conTorneoGanados(1)
                .conGoles(3)
                .build();

        // act - assert
        mocMvc.perform(patch("/jugador/calificar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(historial)))
                .andExpect(status().isOk())
                .andDo(
                        result -> {
                            String resultado = result.getResponse().getContentAsString();
                            JSONObject jsonObject = new JSONObject(resultado);

                            mocMvc.perform(get("/jugador/historial/by")
                                    .param("id", jsonObject.getString("valor")))
                                    .andDo(
                                            resultHistorial -> {
                                                MvcResult requestResult = mocMvc.perform(get("/jugador/by")
                                                        .param("identificacion", "1116745412"))
                                                        .andExpect(status().isOk())
                                                        .andReturn();

                                                String contentAsString = requestResult.getResponse().getContentAsString();
                                                contentAsString = contentAsString.replace("[", "");
                                                contentAsString = contentAsString.replace("]", "");
                                                JSONObject updateJugadorResponse = new JSONObject(contentAsString);
                                                assertEquals(1116745412, updateJugadorResponse.getInt("numeroIdentificacion"));
                                                assertEquals(590, updateJugadorResponse.getInt("minutosJugados"));
                                                assertEquals(3, updateJugadorResponse.getInt("torneosGanados"));
                                                assertEquals(8, updateJugadorResponse.getInt("goles"));
                                            }
                                    )
                                    .andExpect(status().isOk());
                        });
    }
}
