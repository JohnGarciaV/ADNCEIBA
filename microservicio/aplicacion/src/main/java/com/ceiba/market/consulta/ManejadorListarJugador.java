package com.ceiba.market.consulta;

import com.ceiba.market.modelo.dto.DtoJugador;
import com.ceiba.market.puerto.dao.DaoJugador;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarJugador {

    private final DaoJugador daoJugador;

    public ManejadorListarJugador(DaoJugador daoJugador){
        this.daoJugador = daoJugador;
    }

    public List<DtoJugador> ejecutar(){ return this.daoJugador.listar(); }

    public List<DtoJugador> ejecutar(int numeroDocumento){
        return this.daoJugador.listarPorNumeroDocumento(numeroDocumento);
    }

}
