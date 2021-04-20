package com.ceiba.market.puerto.dao;
import com.ceiba.market.modelo.dto.DtoHistorial;

import java.util.List;

public interface DaoHistorial {

    /**
     * Permite listar historial
     * @return dtoJugador
     */
    List<DtoHistorial> listar();

    /**
     * Permite listar historial por numero de documento
     * @return dtoJugador
     */
    List<DtoHistorial> listarPorNumeroDocumento(int numeroDocumento);

    /**
     * Permite listar historial por id historial
     * @return dtoJugador
     */
    List<DtoHistorial> listarPorId(Long id);
}
