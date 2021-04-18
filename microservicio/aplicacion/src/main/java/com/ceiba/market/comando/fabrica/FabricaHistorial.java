package com.ceiba.market.comando.fabrica;

import com.ceiba.market.comando.ComandoHistorial;
import com.ceiba.market.modelo.entidad.Historial;
import org.springframework.stereotype.Component;

@Component
public class FabricaHistorial {

    public Historial crear(ComandoHistorial comandoHistorial){

        return new Historial(comandoHistorial.getIdHistorial(), comandoHistorial.getNumeroIdentificacion(),
                comandoHistorial.getValorTransferencia(), comandoHistorial.getFechaTransferencia(),
                comandoHistorial.getFechaCalificacion(), comandoHistorial.getEquipoFutbolAnterior(),
                comandoHistorial.getEquipoFutbolTraspaso(), comandoHistorial.getMinutosJugados(),
                comandoHistorial.getTorneoGanados(), comandoHistorial.getGoles());

    }
}
