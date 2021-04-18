CREATE TABLE jugador (
    id_Jugador int(11) NOT NULL AUTO_INCREMENT,
	numero_identificacion int(11) NOT NULL,
	edad int(2) NOT NULL,
	valorizacion DECIMAL(20,2) NOT NULL,
	calificacion DECIMAL(20,2),
	fecha_inicio_temporada TIMESTAMP(10),
	fecha_fin_temporada TIMESTAMP(10),
	fecha_valorizacion TIMESTAMP(10),
	equipo_futbol VARCHAR(30),
	minutos_jugados int(5) NOT NULL,
	torneos_ganados int(5) NOT NULL,
	goles int(5) NOT NULL,
    PRIMARY KEY (id_Jugador)
);

CREATE TABLE historial (
    id_historial int(11) NOT NULL AUTO_INCREMENT,
	numero_identificacion int(11) NOT NULL,
	valor_transferencia DECIMAL(20,2) NOT NULL,
	fecha_transferencia TIMESTAMP(10),
	fecha_calificacion TIMESTAMP(10),
	fecha_valorizacion TIMESTAMP(10),
	equipo_futbol_anterior VARCHAR(30),
	equipo_futbol_traspaso VARCHAR(30),
	minutos_jugados int(4) NOT NULL,
	torneos_ganados int(4) NOT NULL,
	goles int(4) NOT NULL,
    PRIMARY KEY (id_historial)
);