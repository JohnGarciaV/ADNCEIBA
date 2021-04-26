package com.ceiba.market.servicio;

import com.ceiba.dominio.excepcion.ExcepcionNoExiste;
import com.ceiba.dominio.excepcion.ExcepcionNoExisteDato;
import com.ceiba.market.modelo.dto.DtoHistorial;
import com.ceiba.market.modelo.dto.DtoJugador;
import com.ceiba.market.modelo.entidad.Historial;
import com.ceiba.market.modelo.entidad.Jugador;
import com.ceiba.market.puerto.dao.DaoHistorial;
import com.ceiba.market.puerto.dao.DaoJugador;
import com.ceiba.market.puerto.repositorio.RepositorioHistorial;
import com.ceiba.market.puerto.repositorio.RepositorioJugador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger LOGGER = LoggerFactory.getLogger(ServicioCalificarJugador.class);
    public static final String FORMATO_ESTANDAR_HORA = " 00:00:00.0";

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
        Jugador jugador = existeJugador(historial);
        Historial ultimoHistorial = existeHistorial(historial, jugador);
        historial = validarDiaCalificacion(historial, ultimoHistorial);
        historial = actualizarCalificacionJugador(historial, ultimoHistorial, jugador);
        return this.repositorioHistorial.crear(historial);
    }

    public Historial existeHistorial(Historial historial, Jugador jugador){
        List<DtoHistorial> listDtoHistorial = this.daoHistorial.listarPorNumeroDocumento(historial.getNumeroIdentificacion());
        Historial ultimoHistorial;
        if(!listDtoHistorial.isEmpty()) {
            DtoHistorial ultimoHistorialDto = listDtoHistorial.get(0);
             ultimoHistorial = new Historial(ultimoHistorialDto.getIdHistorial(),
                    ultimoHistorialDto.getNumeroIdentificacion(), ultimoHistorialDto.getValorTransferencia(),
                    ultimoHistorialDto.getFechaTransferencia(), ultimoHistorialDto.getFechaCalificacion(),
                    ultimoHistorialDto.getEquipoFutbolAnterior(), ultimoHistorialDto.getEquipoFutbolActual(),
                    ultimoHistorialDto.getMinutosJugados(), ultimoHistorialDto.getTorneoGanados(),
                    ultimoHistorialDto.getGoles());
        }else{
            int minutosTemporal = historial.getMinutosJugados();
            int torneosGanados = historial.getTorneoGanados();
            int goles = historial.getGoles();
            LocalDate fechaActual = LocalDate.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern(FORMATO_FECHA);
            fechaActual = fechaActual.minusDays(1);
            String formatoFechaActual = formato.format(fechaActual);
            historial.setMinutosJugados(0);
            historial.setTorneoGanados(0);
            historial.setGoles(0);
            historial.setValorTransferencia(jugador.getValorizacion());
            historial.setFechaTransferencia(jugador.getFechaValorizacion());
            historial.setFechaCalificacion(formatoFechaActual);
            historial.setEquipoFutbolAnterior(jugador.getEquipoFutbol());
            historial.setEquipoFutbolActual(jugador.getEquipoFutbol());
            this.repositorioHistorial.crear(historial);
            historial.setMinutosJugados(minutosTemporal);
            historial.setTorneoGanados(torneosGanados);
            historial.setGoles(goles);
            ultimoHistorial = historial;
        }
        return ultimoHistorial;
    }

    public Historial validarDiaCalificacion(Historial historial, Historial ultimoHistorial) {

        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern(FORMATO_FECHA);
        String formatoFechaActual = formato.format(fechaActual);
        LocalDate fechaActualFormateada = LocalDate.parse(formatoFechaActual, formato);

        String ultimaFechaActual = ultimoHistorial.getFechaCalificacion().replaceAll(FORMATO_ESTANDAR_HORA, "");
        LocalDate ultimaCalificacion = LocalDate.parse(ultimaFechaActual, formato);
        historial.setFechaCalificacion(formatoFechaActual+ FORMATO_ESTANDAR_HORA);
        if (fechaActualFormateada.isEqual(ultimaCalificacion) || fechaActualFormateada.isBefore(ultimaCalificacion)) {
            LOGGER.error(EXISTE_CALIFICACION);
            throw new ExcepcionNoExisteDato(EXISTE_CALIFICACION);
        }
        return historial;
    }

    public Jugador existeJugador(Historial historial){
        List<DtoJugador> listDtoJugador = this.daoJugador.listarPorNumeroDocumento(historial.getNumeroIdentificacion());
        Jugador jugador = null;
        if(!listDtoJugador.isEmpty()){
            DtoJugador dtoJugador = listDtoJugador.get(0);

             jugador = new Jugador(dtoJugador.getIdJugador(), dtoJugador.getNombre(), dtoJugador.getNumeroIdentificacion(),
                    dtoJugador.getEdad(), dtoJugador.getValorizacion().replace(".00", ""),
                    dtoJugador.getCalificacion(), dtoJugador.getFechaInicioTemporada().replaceAll(FORMATO_ESTANDAR_HORA, ""),
                    dtoJugador.getFechaFinTemporada().replaceAll(FORMATO_ESTANDAR_HORA, ""),
                    dtoJugador.getFechaValorizacion().replaceAll(FORMATO_ESTANDAR_HORA, ""),
                    dtoJugador.getEquipoFutbol(), dtoJugador.getMinutosJugados(),
                    dtoJugador.getTorneoGanados(), dtoJugador.getGoles());
        }else{
            LOGGER.error(NO_EXISTE_JUGADOR);
            throw new ExcepcionNoExiste(NO_EXISTE_JUGADOR);
        }
        return jugador;
    }

    public Historial actualizarCalificacionJugador(Historial historial, Historial ultimoHistorial, Jugador jugador){

           Double calificacionActual = jugador.getCalificacion();
           int minutos = historial.getMinutosJugados() + jugador.getMinutosJugados();
           int torneos = historial.getTorneoGanados() + jugador.getTorneoGanados();
           int goles = historial.getGoles() + jugador.getGoles();
           calificacionActual = calcularCalificacion(calificacionActual, minutos, torneos, goles);

           jugador.setCalificacion(calificacionActual);
           jugador.setMinutosJugados(minutos);
           jugador.setTorneoGanados(torneos);
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
}
