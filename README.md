# Trivial-

## Problema 0

Extraemos el codigo duplicado a un unico metodo "nuevaPosicionJugador",
al que llamamos desde ambos sitios

Creamos test unitario "si_al_principio_saco_un_1_voy_a_la_casilla_1
"

## Problema 1 / 2

Creamos un metodo boolean para que devuelva false si los jugadores son mas de 6 o menos d 2

## Problema 4

Cambiamos en la clase "Game Launcher" el metodo "noGanador" para que sea
"if (rand.nextInt(9) != 7)" en lugar de "if (rand.nextInt(9) == 7)" e invertimos los
if y else de posicion.

## Problema 5

Extraemos la linea "System.out.println(jugadores.get(jugadorActual) + " es el jugador actual");" a
un metodo "jugadorActual"

## Problema 6

Extraemos las lineas repetidas a un metodo "if (jugadorActual == jugadores.size()) jugadorActual = 0;"