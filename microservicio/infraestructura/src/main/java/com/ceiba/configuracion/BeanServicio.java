package com.ceiba.configuracion;

import com.ceiba.market.puerto.dao.DaoHistorial;
import com.ceiba.market.puerto.dao.DaoJugador;
import com.ceiba.market.puerto.repositorio.*;
import com.ceiba.market.servicio.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {
    @Bean
    public ServicioCrearJugador servicioCrearJugador(RepositorioJugador repositorioJugador){
        return new ServicioCrearJugador(repositorioJugador);
    }

    @Bean
    public ServicioValorizarJugador servicioActualizarJugador(RepositorioJugador repositorioJugador) {
        return new ServicioValorizarJugador(repositorioJugador);
    }

    @Bean
    public ServicioCalificarJugador servicioCalificarJugador(RepositorioHistorial repositorioHistorial,
                                                             DaoHistorial daoHistorial, DaoJugador daoJugador,
                                                             RepositorioJugador repositorioJugador) {
        return new ServicioCalificarJugador(repositorioHistorial, daoHistorial, daoJugador, repositorioJugador);
    }
}
