package com.example.demo;

import java.util.List;

public class Batalla {
    private Pokemon jugador;
    private Pokemon rival;
    private Inventario inventario;

    public Batalla(Pokemon jugador, Pokemon rival, Inventario inventario) {
        this.jugador = jugador;
        this.rival = rival;
        this.inventario = inventario;
    }

    public void iniciarBatalla() {
        System.out.println("\nInicia la batalla: " + jugador.getNombre() + " vs " + rival.getNombre());

        while (jugador.getVida() > 0 && rival.getVida() > 0) {
            jugador.atacar(0, rival);

            if (rival.getVida() <= 0) {
                rival.destruye();
                break;
            }

            rival.atacar(0, jugador);

            if (jugador.getVida() <= 0) {
                jugador.destruye();
                break;
            }
        }

        mostrarResultado();
    }

    private void mostrarResultado() {
        System.out.println("\nResultado de la batalla:");
        System.out.println(jugador.getNombre() + " HP: " + jugador.getVida());
        System.out.println(rival.getNombre() + " HP: " + rival.getVida());

        if (jugador.getVida() > 0) {
            System.out.println("Ganaste la batalla.");
        } else {
            System.out.println("Perdiste la batalla.");
        }
    }
}
