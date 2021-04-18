package com.ceiba.market.comando.fabrica;

import com.ceiba.market.comando.ComandoJugador;
import com.ceiba.market.modelo.entidad.Jugador;
import org.springframework.stereotype.Component;

@Component
public class FabricaJugador {
    public Jugador crear(ComandoJugador comandoJugador){

        return new Jugador(comandoJugador.getIdJugador(),comandoJugador.getNumeroIdentificacion(),
                comandoJugador.getEdad(), comandoJugador.getValorizacion(), comandoJugador.getCalificacion(),
                comandoJugador.getFechaInicioTemporada(), comandoJugador.getFechaFinTemporada(),
                comandoJugador.getFechaValorizacion(), comandoJugador.getEquipoFutbol(), comandoJugador.getMinutosJugados(),
                comandoJugador.getTorneosGanados(), comandoJugador.getGoles());

    }
}
