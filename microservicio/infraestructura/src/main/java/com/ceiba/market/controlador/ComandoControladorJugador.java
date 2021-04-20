package com.ceiba.market.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.market.comando.ComandoHistorial;
import com.ceiba.market.comando.ComandoJugador;
import com.ceiba.market.comando.manejador.jugador.ManejadorActualizarJugador;
import com.ceiba.market.comando.manejador.jugador.ManejadorCrearJugador;
import com.ceiba.market.comando.manejador.jugador.ManejoCalificarJugador;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jugador")
@Api(tags = { "Controlador comando Jugador"})
public class ComandoControladorJugador {

    private final ManejadorCrearJugador manejadorCrearJugador;
    private final ManejadorActualizarJugador manejadorActualizarJugador;
    private final ManejoCalificarJugador manejoCalificarJugador;

    @Autowired
    public ComandoControladorJugador(ManejadorCrearJugador manejadorCrearJugador,
                                     ManejadorActualizarJugador manejadorActualizarJugador,
                                     ManejoCalificarJugador manejoCalificarJugador) {
        this.manejadorCrearJugador = manejadorCrearJugador;
        this.manejadorActualizarJugador = manejadorActualizarJugador;
        this.manejoCalificarJugador = manejoCalificarJugador;
    }

    @PostMapping
    @ApiOperation("Crear Jugador")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoJugador comandoJugador) {
        return manejadorCrearJugador.ejecutar(comandoJugador);
    }

    @PutMapping("/valorar")
    @ApiOperation("valorizar Jugador")
    public boolean valorizar(@RequestBody ComandoJugador comandoJugador)
    {
       manejadorActualizarJugador.ejecutar(comandoJugador);
       return true;
    }

    @PatchMapping("/calificar")
    @ApiOperation("Calificar Jugador")
    public ComandoRespuesta<Long> calificar(@RequestBody ComandoHistorial comandoHistorial) {
        return manejoCalificarJugador.ejecutar(comandoHistorial);
    }
}
