package com.ceiba.market.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.market.modelo.dto.DtoHistorial;
import com.ceiba.market.puerto.dao.DaoHistorial;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoHistorialH2 implements DaoHistorial {

    private static final String CAMPO_NUMERO_IDENTIFICACION = "numeroIdentificacion";
    private static final String CAMPO_ID = "idHistorial";
    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace= "jugador", value="listarHistorial")
    private static String sqlListar;

    @SqlStatement(namespace= "jugador", value="listarHistorialPorNumeroDocumento")
    private static String sqlListarHistorialOrdenado;

    @SqlStatement(namespace= "jugador", value="listarHistorialPorId")
    private static String sqlListarHistorialPorId;

    public DaoHistorialH2(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoHistorial> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar,
                new MapeoHistorial());
    }

    @Override
    public List<DtoHistorial> listarPorNumeroDocumento(int numeroDocumento) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue(CAMPO_NUMERO_IDENTIFICACION, numeroDocumento);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarHistorialOrdenado,
                paramSource, new MapeoHistorial());

    }
    @Override
    public List<DtoHistorial> listarPorId(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue(CAMPO_ID, id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarHistorialPorId,
                paramSource, new MapeoHistorial());
    }
}
