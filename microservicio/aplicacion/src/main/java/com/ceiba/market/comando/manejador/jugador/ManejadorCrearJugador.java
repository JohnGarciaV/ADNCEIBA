package com.ceiba.market.comando.manejador.jugador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.market.comando.ComandoJugador;
import com.ceiba.market.comando.fabrica.FabricaJugador;
import com.ceiba.market.modelo.entidad.Jugador;
import com.ceiba.market.servicio.ServicioCrearJugador;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearJugador implements ManejadorComandoRespuesta<ComandoJugador, ComandoRespuesta<Long>> {

    private final FabricaJugador fabricaJugador;
    private final ServicioCrearJugador servicioCrearJugador;

    public ManejadorCrearJugador(FabricaJugador fabricaJugador, ServicioCrearJugador servicioCrearJugador) {
        this.fabricaJugador = fabricaJugador;
        this.servicioCrearJugador = servicioCrearJugador;
    }

    public ComandoRespuesta<Long> ejecutar(ComandoJugador comandoJugador)
    {
        Jugador jugador = this.fabricaJugador.crear(comandoJugador);
        return new ComandoRespuesta<>(this.servicioCrearJugador.ejecutar(jugador));
    }
}
