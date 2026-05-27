package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Nivel {

    private String nombre;
    private int numero;
    private String dificultad;

    private List<Obstaculo> obstaculos;
    private List<CheckPoint> checkPoints;
    private List<ElementoDinamico> elementosDinamicos;

    private Inventario inventario;

    // Pokémon del nivel
    private Pokemon rival;

    // Constructor
    public Nivel(String nombre,
                 int numero,
                 String dificultad,
                 Inventario inventario,
                 Pokemon rival) {

        this.nombre = nombre;
        this.numero = numero;
        this.dificultad = dificultad;
        this.inventario = inventario;
        this.rival = rival;

        this.obstaculos = new ArrayList<>();
        this.checkPoints = new ArrayList<>();
        this.elementosDinamicos = new ArrayList<>();
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public int getNumero() {
        return numero;
    }

    public String getDificultad() {
        return dificultad;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public Pokemon getRival() {
        return rival;
    }

    public List<Obstaculo> getObstaculos() {
        return obstaculos;
    }


    public void agregarObstaculo(Obstaculo obstaculo) {
        obstaculos.add(obstaculo);
    }

    public void agregarCheckPoint(CheckPoint checkPoint) {
        checkPoints.add(checkPoint);
    }

    public void agregarElementoDinamico(ElementoDinamico elemento) {
        elementosDinamicos.add(elemento);
    }

    public void moverElementosDinamicos() {

        String[] direcciones = {"norte", "sur", "este", "oeste"};

        Random random = new Random();

        for (ElementoDinamico elemento : elementosDinamicos) {

            String direccion =
                    direcciones[random.nextInt(direcciones.length)];

            int distancia = random.nextInt(5) + 1;

            elemento.mover(direccion, distancia);
        }
    }

    public void aplicarObstaculos(Pokemon jugador) {

        for (Obstaculo obstaculo : obstaculos) {

            if (!obstaculo.isDestruido()) {

                jugador.recibirDaño(obstaculo.getDano());

                System.out.println(
                        jugador.getNombre()
                                + " chocó con "
                                + obstaculo.getNombre()
                                + " y recibió "
                                + obstaculo.getDano()
                                + " de daño."
                );
            }
        }
    }

    public void mostrarEstado() {

        System.out.println("\n==============================");
        System.out.println("Nivel: " + nombre);
        System.out.println("Número: " + numero);
        System.out.println("Dificultad: " + dificultad);

        System.out.println("\nObstáculos: "
                + obstaculos.size());

        System.out.println("CheckPoints: "
                + checkPoints.size());

        System.out.println("Elementos dinámicos: "
                + elementosDinamicos.size());

        System.out.println("==============================");
    }
    public void activarCheckPoints(Pokemon jugador,
                                   Pokemon rival) {

        for (CheckPoint checkpoint : checkPoints) {

            checkpoint.activar();
        }
    }
}
