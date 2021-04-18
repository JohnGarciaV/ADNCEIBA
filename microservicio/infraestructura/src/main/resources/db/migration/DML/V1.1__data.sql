insert into jugador (id_jugador,numero_identificacion,edad,
valorizacion,calificacion,fecha_Inicio_Temporada,fecha_Fin_Temporada,
fecha_valorizacion, equipo_Futbol, minutos_Jugados, torneos_Ganados, goles)
values(1,'1116247957',21,100000000,0,'2021-01-28','2021-06-30', '2021-04-13',
'Cortulua', 500, 2, 5);

insert into jugador (id_jugador,numero_identificacion,edad,
valorizacion,calificacion,fecha_Inicio_Temporada,fecha_Fin_Temporada,
fecha_valorizacion, equipo_Futbol, minutos_Jugados, torneos_Ganados, goles)
values(2, '1116745412', 21, 500000, 0,'2021-01-28','2021-06-30', '2021-03-01',
'Cortulua', 100, 2, 5);

insert into historial (id_historial,numero_identificacion,valor_transferencia,
fecha_transferencia,fecha_calificacion,equipo_futbol_anterior,equipo_futbol_traspaso,
minutos_jugados, torneos_ganados, goles)
values(1, '1116745412', 100000000, '2021-01-28','2020-01-16', 'Orsomarso',
'Pereira', 90, 0, 1);

insert into historial (id_historial,numero_identificacion,valor_transferencia,
fecha_transferencia,fecha_calificacion,equipo_futbol_anterior,equipo_futbol_traspaso,
minutos_jugados, torneos_ganados, goles)
values(2, '1116745412', 300000000, '2021-01-28','2020-01-23', 'Orsomarso',
'Pereira', 180, 0, 2);

insert into historial (id_historial,numero_identificacion,valor_transferencia,
fecha_transferencia,fecha_calificacion,equipo_futbol_anterior,equipo_futbol_traspaso,
minutos_jugados, torneos_ganados, goles)
values(3, '1116745412', 1000000000, '2021-02-02','2021-04-16', 'Pereira',
'Cortulua', 270, 0, 2);