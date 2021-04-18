package com.ceiba.market.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.market.modelo.entidad.Historial;
import com.ceiba.market.puerto.repositorio.RepositorioHistorial;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioHistorialH2 implements RepositorioHistorial {

    private static final String NAMESPACE = "jugador";
    private static final String CAMPO_NUMERO_IDENTIFICACION = "numeroIdentificacion";

    @SqlStatement(namespace= NAMESPACE, value="crearHistorial")
    private static String sqlCrear;

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    public RepositorioHistorialH2(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Historial historial) {
        return this.customNamedParameterJdbcTemplate.crear(historial, sqlCrear);
    }

    @Override
    public boolean existe(int numeroDocumento) {
        return false;
    }
}
