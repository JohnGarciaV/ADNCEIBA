INSERT INTO historial (numero_identificacion,valor_transferencia, fecha_transferencia, fecha_calificacion,
equipo_futbol_anterior, equipo_futbol_traspaso, minutos_jugados, torneos_ganados, goles)
values (:numeroIdentificacion,:valorTransferencia,:fechaTransferencia,:fechaCalificacion,
:equipoFutbolAnterior,:equipoFutbolActual,:minutosJugados,:torneoGanados, :goles)