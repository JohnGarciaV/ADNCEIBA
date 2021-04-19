update jugador
set calificacion = :calificacion,
minutos_jugados= :minutosJugados,
torneos_ganados= :torneosGanados,
goles= :goles
where id_jugador = :idJugador;