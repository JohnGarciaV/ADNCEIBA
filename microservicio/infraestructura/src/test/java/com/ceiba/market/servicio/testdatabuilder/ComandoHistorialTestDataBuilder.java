package com.ceiba.market.servicio.testdatabuilder;

import com.ceiba.market.comando.ComandoHistorial;

public class ComandoHistorialTestDataBuilder {

    private Long idHistorial;
    private int numeroIdentificacion;
    private String valorTransferencia;
    private String fechaTransferencia;
    private String fechaCalificacion;
    private String equipoFutbolAnterior;
    private String equipoFutbolActual;
    private int minutosJugados;
    private int torneoGanados;
    private int goles;

    public ComandoHistorialTestDataBuilder(){
            this.idHistorial = 111L;
            this.numeroIdentificacion = 1116745412;
            this.valorTransferencia = "2021-01-28 00:00:00.0";
            this.fechaTransferencia = "2020-01-16 00:00:00.0";
            this.fechaCalificacion = "2020-04-16 00:00:00.0";
            this.equipoFutbolAnterior = "Orsomarso";
            this.equipoFutbolActual = "Pereira";
            this.minutosJugados = 90;
            this.torneoGanados = 0;
            this.goles = 1;
    }

        public ComandoHistorialTestDataBuilder conIdHistorial(Long idHistorial) {
            this.idHistorial = idHistorial;
            return this;
        }

        public ComandoHistorialTestDataBuilder conNumeroIdentificacion(int numeroIdentificacion) {
            this.numeroIdentificacion = numeroIdentificacion;
            return this;
        }

        public ComandoHistorialTestDataBuilder conValorTransferencia(String valorTransferencia) {
            this.valorTransferencia = valorTransferencia;
            return this;
        }

        public ComandoHistorialTestDataBuilder conFechaTransferencia(String fechaTransferencia) {
            this.fechaTransferencia = fechaTransferencia;
            return this;
        }

        public ComandoHistorialTestDataBuilder conFechaCalificacion(String fechaCalificacion) {
            this.fechaCalificacion = fechaCalificacion;
            return this;
        }

        public ComandoHistorialTestDataBuilder conEquipoFutbolAnterior(String equipoFutbolAnterior) {
            this.equipoFutbolAnterior = equipoFutbolAnterior;
            return this;
        }

        public ComandoHistorialTestDataBuilder conEquipoFutbolActual(String equipoFutbolActual) {
            this.equipoFutbolActual = equipoFutbolActual;
            return this;
        }

        public ComandoHistorialTestDataBuilder conMinutosJugados(int minutosJugados) {
            this.minutosJugados = minutosJugados;
            return this;
        }

        public ComandoHistorialTestDataBuilder conTorneoGanados(int torneoGanados) {
            this.torneoGanados = torneoGanados;
            return this;
        }

        public ComandoHistorialTestDataBuilder conGoles(int goles) {
            this.goles = goles;
            return this;
        }

        public ComandoHistorial build()
        {
            return new ComandoHistorial(this.idHistorial, this.numeroIdentificacion, this.valorTransferencia,
                    this.fechaTransferencia, this.fechaCalificacion, this.equipoFutbolAnterior,
                    this.equipoFutbolActual, this.minutosJugados,  this.torneoGanados, this.goles);
        }
}
