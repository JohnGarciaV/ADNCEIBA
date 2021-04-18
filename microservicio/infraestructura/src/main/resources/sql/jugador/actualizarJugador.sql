update jugador
set valorizacion = :valorizacion,
    fecha_valorizacion = :fechaValorizacion
where id_jugador = :idJugador;