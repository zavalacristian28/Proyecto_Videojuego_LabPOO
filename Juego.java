package com.example.demo;

import java.util.Scanner;

public class Juego {

    private Scanner scanner;

    private Pokemon jugador;
    private Nivel nivel;

    public Juego() {
        scanner = new Scanner(System.in);
    }

    public void iniciar() {

        System.out.println("============================");
        System.out.println("      BATALLA POKÉMON");
        System.out.println("============================");

        crearJugador();
        crearNivel();

        nivel.mostrarEstado();

        nivel.aplicarObstaculos(jugador);

        Batalla batalla = new Batalla(jugador, nivel.getRival(), nivel.getInventario()
        );

        batalla.iniciarBatalla();

        GestorArchivos.guardarPartida("partida.txt", jugador, nivel.getRival()
        );
    }

    private void crearJugador() {

        jugador = new Pokemon("Houndoom", 150, 0, 0, 80, 40
        );

        jugador.aprenderMovimiento(new Movimiento("Lanzallamas", 50)
        );

        jugador.aprenderMovimiento(new Movimiento("Triturar", 40)
        );
    }

    private void crearNivel() {

        Pokemon rival = new Pokemon("Pidgeot", 160, 0, 0, 75, 50);

        rival.aprenderMovimiento(new Movimiento("Vendaval", 55));

        rival.aprenderMovimiento(new Movimiento("Ataque Ala", 35));

        Inventario inventario = new Inventario(5);

        Recompensa pocion = new Recompensa("Poción", 40, "Curación");

        inventario.agregarItem(pocion);

        nivel = new Nivel("Bosque Pokémon", 1, "Normal", inventario, rival);

        nivel.agregarObstaculo(new Obstaculo("Roca", 10, 3, 5));

        nivel.agregarElementoDinamico(
                new Utileria("Pokeball", "Objeto decorativo", 2, 4));
    }

}
