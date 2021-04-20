package com.ceiba.market.controlador;

import com.ceiba.ApplicationMock;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= ApplicationMock.class)
@WebMvcTest(ConsultaControladorJugador.class)
public class ConsultaControladorJugadorTest {

    @Autowired
    private MockMvc mocMvc;

    @Test
    public void listar() throws Exception {
        // arrange

        // act - assert
        String response = mocMvc.perform(get("/jugador")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
    }

    @Test
    public void listarHistorial() throws Exception {
        // arrange

        // act - assert
        String response = mocMvc.perform(get("/jugador/historial")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
    }

    @Test
    public void listarPorNumeroDocumento() throws Exception {
        // arrange

        // act - assert
        String response = mocMvc.perform(get("/jugador/by")
                .param("identificacion", "1116745412"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        response = response.replace("[", "");
        response = response.replace("]", "");
        JSONObject jsonObject = new JSONObject(response);
        assertEquals(1116745412, jsonObject.getInt("numeroIdentificacion"));
    }

    @Test
    public void listarHistorialPorNumeroDocumento() throws Exception {
        // arrange

        // act - assert
        String response = mocMvc.perform(get("/jugador/historial/by")
                .param("id", "1"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        response = response.replace("[", "");
        response = response.replace("]", "");
        JSONObject jsonObject = new JSONObject(response);
        assertEquals(1116745412, jsonObject.getInt("numeroIdentificacion"));
    }
}
