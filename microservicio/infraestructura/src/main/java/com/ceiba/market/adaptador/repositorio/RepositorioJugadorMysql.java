package com.ceiba.market.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.market.modelo.entidad.Jugador;
import com.ceiba.market.puerto.repositorio.RepositorioJugador;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioJugadorMysql implements RepositorioJugador {

    private static final String NAMESPACE = "jugador";
    private static final String CREAR = "crearJugador";
    private static final String ACTUALIZAR = "actualizarJugador";
    private static final String ACTUALIZARCALIFICACION = "actualizarCalificacionJugador";
    private static final String EXISTE = "existeJugador";
    private static final String EXISTE_TEMPORADA = "existeTemporada";
    private static final String CAMPO_NUMERO_IDENTIFICACION = "numeroIdentificacion";
    private static final String CAMPO_FECHA_ACTUAL = "fechaActual";

    @SqlStatement(namespace= NAMESPACE, value=CREAR)
    private static String sqlCrear;

    @SqlStatement(namespace= NAMESPACE, value=ACTUALIZAR)
    private static String sqlActualizar;

    @SqlStatement(namespace= NAMESPACE, value=ACTUALIZARCALIFICACION)
    private static String sqlActualizarCalificacion;

    @SqlStatement(namespace= NAMESPACE, value=EXISTE)
    private static String sqlExiste;

    @SqlStatement(namespace= NAMESPACE, value=EXISTE_TEMPORADA)
    private static String sqlExisteTemporada;

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    public RepositorioJugadorMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Jugador jugador) {
        return   this.customNamedParameterJdbcTemplate.crear(jugador, sqlCrear);
    }

    @Override
    public void actualizar(Jugador jugador) {
        this.customNamedParameterJdbcTemplate.actualizar(jugador, sqlActualizar);
    }

    @Override
    public void actualizarCalificacion(Jugador jugador) {
        this.customNamedParameterJdbcTemplate.actualizar(jugador, sqlActualizarCalificacion);
    }

    @Override
    public boolean existe(int numeroIdentificacion) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue(CAMPO_NUMERO_IDENTIFICACION, numeroIdentificacion);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExiste,paramSource, Boolean.class);
    }

    @Override
    public Jugador consultarTemporadaPornumeroDocumento(int numeroDocumento, String fechaActual) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue(CAMPO_NUMERO_IDENTIFICACION, numeroDocumento);
        paramSource.addValue(CAMPO_FECHA_ACTUAL, fechaActual);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExisteTemporada,paramSource, Jugador.class);
    }

}
