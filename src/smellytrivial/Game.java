package smellytrivial;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
    ArrayList jugadores = new ArrayList(6);
    int[] posiciones = new int[6];
    int[] monederos = new int[6];
    boolean[] enCasillaCastigo = new boolean[6];

    LinkedList preguntasCultura = new LinkedList();
    LinkedList preguntasCiencias = new LinkedList();
    LinkedList preguntasDeportes = new LinkedList();
    LinkedList preguntasMusica = new LinkedList();

    int jugadorActual = 0;
    boolean estaSaliendoDeLaCarcel;

    public Game() {
        for (int i = 0; i < 50; i++) {
            preguntasCultura.addLast("Pregunta de Cultura " + i);
            preguntasCiencias.addLast(("Pregunta de Ciencias " + i));
            preguntasDeportes.addLast(("Pregunta de Deportes " + i));
            preguntasMusica.addLast(crearPreguntaMusica(i));
        }
    }

    public String crearPreguntaMusica(int index) {
        return "Pregunta de Música " + index;
    }

    public boolean esJugable() {
        if (cuantosJugadores() <= 2 && cuantosJugadores() >= 6) {
            return false;
        } else {
            return true;
        }
    }

    public boolean agregar(String playerName) {


        jugadores.add(playerName);
        posiciones[cuantosJugadores()] = 0;
        monederos[cuantosJugadores()] = 0;
        enCasillaCastigo[cuantosJugadores()] = false;

        jugadorActual(playerName, " se ha unido a la partida");
        System.out.println("Es el jugador número " + jugadores.size());
        return true;
    }

    public int cuantosJugadores() {
        return jugadores.size();
    }

    public void tirarDado(int puntosDado) {
        jugadorActual(jugadores.get(jugadorActual), " es el jugador actual");
        System.out.println("Ha sacado un " + puntosDado);

        if (enCasillaCastigo[jugadorActual]) {
            if (puntosDado % 2 != 0) {
                estaSaliendoDeLaCarcel = true;

                jugadorActual(jugadores.get(jugadorActual), " sale de la casilla de castigo");
                posiciones[jugadorActual] = posiciones[jugadorActual] + puntosDado;
                if (posiciones[jugadorActual] > 11) posiciones[jugadorActual] = posiciones[jugadorActual] - 12;

                nuevaPosicionJugador();
                jugadorActual("La categoría es ", categoriaActual());
                hacerPregunta();
            } else {
                jugadorActual(jugadores.get(jugadorActual), " no sale de la casilla de castigo");
                estaSaliendoDeLaCarcel = false;
            }

        } else {

            posiciones[jugadorActual] = posiciones[jugadorActual] + puntosDado;
            if (posiciones[jugadorActual] > 11) posiciones[jugadorActual] = posiciones[jugadorActual] - 12;

            System.out.println("La nueva posición de "
                    + jugadores.get(jugadorActual)
                    + " es "
                    + posiciones[jugadorActual]);
            jugadorActual("La categoría es ", categoriaActual());
            hacerPregunta();
        }

    }

    private void jugadorActual(Object o, String s) {
        System.out.println(o + s);
    }

    public String nuevaPosicionJugador() {
        return "La nueva posición de "
                + jugadores.get(jugadorActual)
                + " es "
                + posiciones[jugadorActual];
    }


    private void hacerPregunta() {
        if (categoriaActual() == "Cultura popular")
            System.out.println(preguntasCultura.removeFirst());
        if (categoriaActual() == "Ciencias")
            System.out.println(preguntasCiencias.removeFirst());
        if (categoriaActual() == "Deportes")
            System.out.println(preguntasDeportes.removeFirst());
        if (categoriaActual() == "Música")
            System.out.println(preguntasMusica.removeFirst());
    }


    private String categoriaActual() {
        if (posiciones[jugadorActual] == 0) return "Cultura popular";
        if (posiciones[jugadorActual] == 4) return "Cultura popular";
        if (posiciones[jugadorActual] == 8) return "Cultura popular";
        if (posiciones[jugadorActual] == 1) return "Ciencias";
        if (posiciones[jugadorActual] == 5) return "Ciencias";
        if (posiciones[jugadorActual] == 9) return "Ciencias";
        if (posiciones[jugadorActual] == 2) return "Deportes";
        if (posiciones[jugadorActual] == 6) return "Deportes";
        if (posiciones[jugadorActual] == 10) return "Deportes";
        return "Música";
    }

    public boolean fueRespuestaCorrecta() {
        if (enCasillaCastigo[jugadorActual]) {
            if (estaSaliendoDeLaCarcel) {
                System.out.println("Respuesta correcta!!!!");
                monederos[jugadorActual]++;
                System.out.println(jugadores.get(jugadorActual)
                        + " ahora tiene "
                        + monederos[jugadorActual]
                        + " monedas doradas.");

                boolean ganador = jugadorHaGanado();
                jugadorActual++;
                jugadorActual0();

                return ganador;
            } else {
                jugadorActual++;
                jugadorActual0();
                return true;
            }


        } else {

            System.out.println("Respuesta correcta!!!!");
            monederos[jugadorActual]++;
            System.out.println(jugadores.get(jugadorActual)
                    + " ahora tiene "
                    + monederos[jugadorActual]
                    + " monedas doradas.");

            boolean ganador = jugadorHaGanado();
            jugadorActual++;
            jugadorActual0();

            return ganador;
        }
    }

    private void jugadorActual0() {
        if (jugadorActual == jugadores.size()) jugadorActual = 0;
    }

    public boolean respuestaIncorrecta() {
        System.out.println("Respuesta incorrecta");
        jugadorActual(jugadores.get(jugadorActual), " va a la casilla de castigo");
        enCasillaCastigo[jugadorActual] = true;

        jugadorActual++;
        jugadorActual0();
        return true;
    }


    private boolean jugadorHaGanado() {
        return !(monederos[jugadorActual] == 6);
    }
}
