package com.ceiba.market.comando.manejador.jugador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.market.comando.ComandoJugador;
import com.ceiba.market.comando.fabrica.FabricaJugador;
import com.ceiba.market.modelo.entidad.Jugador;
import com.ceiba.market.servicio.ServicioValorizarJugador;
import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizarJugador  implements ManejadorComando<ComandoJugador> {

    private final FabricaJugador fabricaJugador;
    private final ServicioValorizarJugador servicioValorizarJugador;

    public ManejadorActualizarJugador(FabricaJugador fabricaJugador, ServicioValorizarJugador servicioValorizarJugador) {
        this.fabricaJugador = fabricaJugador;
        this.servicioValorizarJugador = servicioValorizarJugador;
    }


    public void ejecutar(ComandoJugador comandoJugador)  {
        try
        {
            Jugador jugador = this.fabricaJugador.crear(comandoJugador);
            this.servicioValorizarJugador.ejecutar(jugador);
        }catch (Exception e)
        {
            throw e;
        }
    }
}