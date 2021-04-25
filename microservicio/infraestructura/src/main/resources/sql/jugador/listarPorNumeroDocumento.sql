select id_jugador,nombre,numero_identificacion,edad,
valorizacion,calificacion,fecha_Inicio_Temporada,fecha_Fin_Temporada,
fecha_valorizacion, equipo_Futbol, minutos_jugados, torneos_ganados, goles
from jugador
where numero_identificacion = :numeroIdentificacion