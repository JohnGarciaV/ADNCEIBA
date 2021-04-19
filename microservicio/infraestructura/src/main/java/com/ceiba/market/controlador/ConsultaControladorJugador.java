package com.ceiba.market.controlador;

import com.ceiba.market.consulta.ManejadorListarJugador;
import com.ceiba.market.consulta.ManejadorListarhistorial;
import com.ceiba.market.modelo.dto.DtoHistorial;
import com.ceiba.market.modelo.dto.DtoJugador;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jugador")
@Api(tags={"Controlador consulta jugadores"})
public class ConsultaControladorJugador {

    private final ManejadorListarJugador manejadorListarJugador;
    private final ManejadorListarhistorial manejadorListarHistorial;

    public ConsultaControladorJugador(ManejadorListarJugador manejadorListarJugador, ManejadorListarhistorial manejadorListarHistorial) {
        this.manejadorListarJugador = manejadorListarJugador;
        this.manejadorListarHistorial = manejadorListarHistorial;
    }

    @GetMapping
    @ApiOperation("Listar Jugadores")
    public List<DtoJugador> listar() {
        return this.manejadorListarJugador.ejecutar();
    }

    @GetMapping("/historial")
    @ApiOperation("Listar Historial")
    public List<DtoHistorial> listarHistorial() {
        return this.manejadorListarHistorial.ejecutar();
    }
}
