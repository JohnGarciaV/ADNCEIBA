package com.ceiba.market.consulta;

import com.ceiba.market.modelo.dto.DtoHistorial;
import com.ceiba.market.puerto.dao.DaoHistorial;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarhistorial {

    private final DaoHistorial daoHistorial;

    public ManejadorListarhistorial(DaoHistorial daoHistorial){
        this.daoHistorial = daoHistorial;
    }

    public List<DtoHistorial> ejecutar(){ return this.daoHistorial.listar(); }

}
