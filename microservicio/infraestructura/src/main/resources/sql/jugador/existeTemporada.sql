select id_jugador,numero_identificacion,edad,
       valorizacion,calificacion,fecha_Inicio_Temporada,fecha_Fin_Temporada,
       fecha_valorizacion, equipo_Futbol, minutos_Jugados, torneos_Ganados, goles
from jugador
where numero_identificacion = :numeroIdentificacion
and fecha_inicio_temporada >= :fechaActual and fecha_fin_temporada <= :fechaActual