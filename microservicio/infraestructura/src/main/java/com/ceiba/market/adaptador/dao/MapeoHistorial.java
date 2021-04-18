package com.ceiba.market.adaptador.dao;

import com.ceiba.market.modelo.dto.DtoHistorial;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoHistorial implements RowMapper<DtoHistorial>, MapperResult {

    public DtoHistorial mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Long idHistorial = resultSet.getLong("id_historial");
        int numeroIdentificacion = resultSet.getInt("numero_identificacion");
        String valorTransferencia = resultSet.getString("valor_transferencia");
        String fechaTransferencia = resultSet.getString("fecha_transferencia");
        String fechaCalificacion = resultSet.getString("fecha_calificacion");
        String equipoFutbolAnterior = resultSet.getString("equipo_futbol_anterior");
        String equipoFutbolActual = resultSet.getString( "equipo_futbol_traspaso");
        int minutosJugados = resultSet.getInt("minutos_jugados");
        int torneoGanado  = resultSet.getInt("torneos_ganados");
        int goles  = resultSet.getInt("goles");

        return new DtoHistorial(idHistorial,numeroIdentificacion,
                valorTransferencia,fechaTransferencia,fechaCalificacion,
                equipoFutbolAnterior,equipoFutbolActual, minutosJugados,
                torneoGanado, goles);
    }
}
