package com.ceiba.market.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.market.modelo.dto.DtoJugador;
import com.ceiba.market.puerto.dao.DaoJugador;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoJugadoresMysql implements DaoJugador {

    private static final String CAMPO_NUMERO_IDENTIFICACION = "numeroIdentificacion";
    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace= "jugador", value="listarJugador")
    private static String sqlListar;

    @SqlStatement(namespace= "jugador", value="listarPorNumeroDocumento")
    private static String sqlListarPorDocumento;


    public DaoJugadoresMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoJugador> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoJugador());
    }

    @Override
    public List<DtoJugador> listarPorNumeroDocumento(int numeroDocumento) {

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue(CAMPO_NUMERO_IDENTIFICACION, numeroDocumento);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarPorDocumento,
                paramSource, new MapeoJugador());
    }
}
