package com.ceiba.market.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.market.modelo.entidad.Jugador;
import com.ceiba.market.puerto.repositorio.RepositorioJugador;

public class ServicioCrearJugador {

    public static final String EL_JUGADOR_YA_FUE_CREADO = "El jugador ya fue creado";

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
            throw new ExcepcionDuplicidad(EL_JUGADOR_YA_FUE_CREADO);
        }
    }
}
