package com.ceiba.market.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.market.modelo.dto.DtoJugador;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoJugador implements RowMapper<DtoJugador>, MapperResult {

    public DtoJugador mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Long idJugador = resultSet.getLong("id_jugador");
        String nombre = resultSet.getString("nombre");
        int numeroIdentificacion = resultSet.getInt("numero_identificacion");
        int edad = resultSet.getInt("edad");
        String valorizacion = resultSet.getString("valorizacion");
        Double calificacion = resultSet.getDouble("calificacion");
        String inicioTemporada = resultSet.getString("fecha_inicio_temporada");
        String finTemporada = resultSet.getString( "fecha_fin_temporada");
        String fechaValorizacion = resultSet.getString( "fecha_valorizacion");
        String equipoFutbol = resultSet.getString("equipo_futbol");
        int minutosJugados = resultSet.getInt("minutos_jugados");
        int torneosGanados = resultSet.getInt("torneos_ganados");
        int goles = resultSet.getInt("goles");


        return new DtoJugador(idJugador,nombre,
                numeroIdentificacion, edad,valorizacion,calificacion,
                inicioTemporada,finTemporada, fechaValorizacion,
                equipoFutbol, minutosJugados, torneosGanados, goles);
    }
}
