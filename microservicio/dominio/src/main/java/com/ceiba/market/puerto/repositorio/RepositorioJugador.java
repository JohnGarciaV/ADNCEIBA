package com.ceiba.market.puerto.repositorio;

import com.ceiba.market.modelo.entidad.Jugador;

public interface RepositorioJugador {

    /**
     * Permite crear un Jugador
     * @param jugador
     * @return el id asignado
     */
    Long crear(Jugador jugador);

    /**
     * Permite actualizar un Jugador
     * @param jugador
     */
    void actualizar(Jugador jugador);

    /**
     * Permite validar si existe un jugador con un numeroDocumento
     * @param numeroDocumento
     * @return boolean
     */
    boolean existe(int numeroDocumento);

    /**
     * Permite consultar las fechas de una temporada por jugador
     * @param numeroDocumento
     * @return boolean
     */
    Jugador consultarTemporadaPornumeroDocumento(int numeroDocumento, String fechaActual);

  }
