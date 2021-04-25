update jugador
set calificacion = :calificacion,
minutos_jugados= :minutosJugados,
torneos_ganados= :torneoGanados,
goles= :goles
where id_jugador = :idJugador;