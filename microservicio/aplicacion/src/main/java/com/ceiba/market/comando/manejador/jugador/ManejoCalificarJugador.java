package com.ceiba.market.comando.manejador.jugador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.market.comando.ComandoHistorial;
import com.ceiba.market.comando.fabrica.FabricaHistorial;
import com.ceiba.market.modelo.entidad.Historial;
import com.ceiba.market.servicio.ServicioCalificarJugador;
import org.springframework.stereotype.Component;

@Component
public class ManejoCalificarJugador implements ManejadorComandoRespuesta<ComandoHistorial, ComandoRespuesta<Long>> {

    private final FabricaHistorial fabricaHistorial;
    private final ServicioCalificarJugador servicioCalificarJugador;

    public ManejoCalificarJugador(FabricaHistorial fabricaHistorial, ServicioCalificarJugador servicioCalificarJugador) {
        this.fabricaHistorial = fabricaHistorial;
        this.servicioCalificarJugador = servicioCalificarJugador;
    }

    public ComandoRespuesta<Long> ejecutar(ComandoHistorial comandoHistorial) {
        Historial historial =  this.fabricaHistorial.crear(comandoHistorial);
        return new ComandoRespuesta<>(this.servicioCalificarJugador.ejecutar(historial));
    }
}
