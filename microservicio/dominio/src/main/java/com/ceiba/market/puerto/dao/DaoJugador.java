package com.ceiba.market.puerto.dao;

import com.ceiba.market.modelo.dto.DtoJugador;

import java.util.List;

public interface DaoJugador {

    /**
     * Permite listar jugadores
     * @return dtoJugador
     */
    List<DtoJugador> listar();

    /**
     * Permite listar jugadores
     * @return dtoJugador
     */
    List<DtoJugador> listarPorNumeroDocumento(int numeroDocumento);
}

