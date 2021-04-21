package com.ceiba.market.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.market.modelo.entidad.Jugador;
import com.ceiba.market.puerto.repositorio.RepositorioJugador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServicioCrearJugador {

    public static final String EL_JUGADOR_YA_FUE_CREADO = "El jugador ya fue creado";
    private static final Logger LOGGER = LoggerFactory.getLogger(ServicioCrearJugador.class);

    private final RepositorioJugador repositorioJugador;

    public ServicioCrearJugador(RepositorioJugador repositorioJugador) {
        this.repositorioJugador = repositorioJugador;
    }

    public Long ejecutar(Jugador jugador) {
        validarExistenciaPrevia(jugador);
        return this.repositorioJugador.crear(jugador);
    }

    private void validarExistenciaPrevia(Jugador jugador) {
        boolean existe = this.repositorioJugador.existe(jugador.getNumeroIdentificacion());
        if(existe) {
            LOGGER.error(EL_JUGADOR_YA_FUE_CREADO);
            throw new ExcepcionDuplicidad(EL_JUGADOR_YA_FUE_CREADO);
        }
    }
}
