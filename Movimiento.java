package com.example.demo;

public class Movimiento {
    private String nombre;
    private int potencia;

    public Movimiento(String nombre, int potencia) {
        this.nombre = nombre;
        this.potencia = potencia;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPotencia() {
        return potencia;
    }
}
