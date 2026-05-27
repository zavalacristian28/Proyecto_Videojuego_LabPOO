package com.example.demo;

public class Arma implements Inventariable {
    private String nombre;  // Nombre del arma
    private int dano;       // Cantidad de daño que causa
    private double alcance; // Distancia máxima de ataque

    // Constructor que inicializa el arma
    public Arma(String nombre, int dano, double alcance) {
        this.nombre = nombre;
        this.dano = dano;
        this.alcance = alcance;
    }

    public String getNombre() { return nombre; }   // Retorna el nombre del arma
    public int getDano() { return dano; }          // Retorna el daño del arma
    public double getAlcance() { return alcance; } // Retorna el alcance del arma

    @Override
    public void registrar() {
        System.out.println("Arma " + nombre + " registrada."); // Implementación de Inventariable
    }

    @Override
    public void borrar() {
        System.out.println("Arma " + nombre + " borrada."); // Implementación de Inventariable
    }
}