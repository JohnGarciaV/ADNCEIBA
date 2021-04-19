package com.ceiba.market.servicio;

import com.ceiba.dominio.excepcion.ExcepcionNoDatoActualizado;
import com.ceiba.dominio.excepcion.ExcepcionNoExiste;
import com.ceiba.dominio.excepcion.ExcepcionNoExisteDato;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.market.modelo.dto.DtoHistorial;
import com.ceiba.market.modelo.dto.DtoJugador;
import com.ceiba.market.modelo.entidad.Historial;
import com.ceiba.market.modelo.entidad.Jugador;
import com.ceiba.market.puerto.dao.DaoHistorial;
import com.ceiba.market.puerto.dao.DaoJugador;
import com.ceiba.market.puerto.repositorio.RepositorioHistorial;
import com.ceiba.market.puerto.repositorio.RepositorioJugador;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ServicioCalificarJugador {

    public static final String NO_EXISTE_HISTORIAL = "El historial a calificar no existe";
    public static final String NO_EXISTE_JUGADOR = "El jugador no existe";
    public static final String EXISTE_CALIFICACION = "Ya se realizó la calificación";
    public static final String JUGADOR_NO_ACTUALIZADO = "Error al actualizar el jugador";
    public static final String NUMERO_NO_POSITIVO = "Los números deben ser positivos";
    public static final Double VALOR_ITEM = 0.2;
    public static final String FORMATO_FECHA =  "yyyy-MM-dd";

    private final RepositorioHistorial repositorioHistorial;
    private final DaoHistorial daoHistorial;
    private final DaoJugador daoJugador;
    private final RepositorioJugador repositorioJugador;

    public ServicioCalificarJugador(RepositorioHistorial repositorioHistorial, DaoHistorial daoHistorial,
                                    DaoJugador daoJugador, RepositorioJugador repositorioJugador) {
        this.repositorioHistorial = repositorioHistorial;
        this.daoHistorial = daoHistorial;
        this.daoJugador = daoJugador;
        this.repositorioJugador = repositorioJugador;
    }

    public Long ejecutar(Historial historial) {
        Historial ultimoHistorial = existeHistorial(historial);
        historial = validarDiaCalificacion(historial, ultimoHistorial);
        validarNumerosPositivos(historial);
        Jugador jugador = existeJugador(historial);
        historial = actualizarCalificacionJugador(historial, ultimoHistorial, jugador);
        return this.repositorioHistorial.crear(historial);
    }

    public Historial existeHistorial(Historial historial){
        List<DtoHistorial> listDtoHistorial = this.daoHistorial.listarPorNumeroDocumento(historial.getNumeroIdentificacion());
        Historial ultimoHistorial;
        if(listDtoHistorial.size() > 0) {
            DtoHistorial ultimoHistorialDto = listDtoHistorial.get(0);
             ultimoHistorial = new Historial(ultimoHistorialDto.getIdHistorial(),
                    ultimoHistorialDto.getNumeroIdentificacion(), ultimoHistorialDto.getValorTransferencia(),
                    ultimoHistorialDto.getFechaTransferencia(), ultimoHistorialDto.getFechaCalificacion(),
                    ultimoHistorialDto.getEquipoFutbolAnterior(), ultimoHistorialDto.getEquipoFutbolActual(),
                    ultimoHistorialDto.getMinutosJugados(), ultimoHistorialDto.getTorneoGanados(),
                    ultimoHistorialDto.getGoles());
        }else{
            throw new ExcepcionNoExisteDato(NO_EXISTE_HISTORIAL);
        }

        return ultimoHistorial;
    }

    public Historial validarDiaCalificacion(Historial historial, Historial ultimoHistorial) {

        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern(FORMATO_FECHA);
        String formatoFechaActual = formato.format(fechaActual);
        LocalDate fechaActualFormateada = LocalDate.parse(formatoFechaActual, formato);

        String ultimaFechaActual = ultimoHistorial.getFechaCalificacion().replaceAll(" 00:00:00.0", "");
        LocalDate ultimaCalificacion = LocalDate.parse(ultimaFechaActual, formato);
        historial.setFechaCalificacion(formatoFechaActual+ " 00:00:00.0");
        if (fechaActualFormateada.isEqual(ultimaCalificacion)) {
            throw new ExcepcionNoExisteDato(EXISTE_CALIFICACION);
        }
        return historial;
    }

    public Jugador existeJugador(Historial historial){
        List<DtoJugador> listDtoJugador = this.daoJugador.listarPorNumeroDocumento(historial.getNumeroIdentificacion());
        Jugador jugador = null;
        if(listDtoJugador.size() > 0){
            DtoJugador dtoJugador = listDtoJugador.get(0);

             jugador = new Jugador(dtoJugador.getIdJugador(), dtoJugador.getNumeroIdentificacion(),
                    dtoJugador.getEdad(), dtoJugador.getValorizacion(), dtoJugador.getCalificacion(),
                    dtoJugador.getFechaInicioTemporada(), dtoJugador.getFechaFinTemporada(),
                    dtoJugador.getFechaValorizacion(), dtoJugador.getEquipoFutbol(), dtoJugador.getMinutosJugados(),
                     dtoJugador.getTorneosGanados(), dtoJugador.getGoles());
        }else{
            throw new ExcepcionNoExiste(NO_EXISTE_JUGADOR);
        }
        return jugador;
    }

    public Historial actualizarCalificacionJugador(Historial historial, Historial ultimoHistorial, Jugador jugador){

           Double calificacionActual = jugador.getCalificacion();
           int minutos = historial.getMinutosJugados() + jugador.getMinutosJugados();
           int torneos = historial.getTorneoGanados() + jugador.getTorneosGanados();
           int goles = historial.getGoles() + jugador.getGoles();
           calificacionActual = calcularCalificacion(calificacionActual, minutos, torneos, goles);

           jugador.setCalificacion(calificacionActual);
           jugador.setMinutosJugados(minutos);
           jugador.setTorneosGanados(torneos);
           jugador.setGoles(goles);

            this.repositorioJugador.actualizarCalificacion(jugador);

            historial.setMinutosJugados(minutos);
            historial.setTorneoGanados(torneos);
            historial.setGoles(goles);
            historial.setValorTransferencia(ultimoHistorial.getValorTransferencia());
            historial.setFechaTransferencia(ultimoHistorial.getFechaTransferencia());
            historial.setEquipoFutbolActual(ultimoHistorial.getEquipoFutbolActual());
            historial.setEquipoFutbolAnterior(ultimoHistorial.getEquipoFutbolAnterior());

        return historial;
    }

    public Double calcularCalificacion(Double calificacion, int minutos, int torneos, int goles){
       int promedioPartido = minutos /90;
        return (torneos*VALOR_ITEM)+ (goles*VALOR_ITEM) + (promedioPartido*VALOR_ITEM)+ calificacion;
    }

    public void validarNumerosPositivos(Historial historial){
        if(historial.getMinutosJugados() < 0 || historial.getTorneoGanados() < 0 || historial.getGoles() < 0){
            throw new ExcepcionValorInvalido(NUMERO_NO_POSITIVO);
        }
    }
}
