package com.ceiba.market.puerto.repositorio;

import com.ceiba.market.modelo.dto.DtoHistorial;
import com.ceiba.market.modelo.entidad.Historial;
import com.ceiba.market.modelo.entidad.Jugador;

import java.util.List;

public interface RepositorioHistorial {

    /**
     * Permite crear un historial
     * @param historial
     * @return el id asignado
     */
    Long crear(Historial historial);

    /**
     * Permite validar si existe un historial por un numeroDocumento
     * @param numeroDocumento
     * @return boolean
     */
    boolean existe(int numeroDocumento);
}