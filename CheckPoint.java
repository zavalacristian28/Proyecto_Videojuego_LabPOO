package com.example.demo;

public class CheckPoint {
    private String nombre;    // Identificador del checkpoint
    private int posicionX;    // Coordenada X en el nivel
    private int posicionY;    // Coordenada Y en el nivel
    private boolean activado; // Indica si el checkpoint ha sido activado

    // Constructor
    public CheckPoint(String nombre, int posicionX, int posicionY) {
        this.nombre = nombre;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.activado = false;
    }

    public String getNombre() { return nombre; }       // Retorna el nombre
    public int getPosicionX() { return posicionX; }    // Retorna coordenada X
    public int getPosicionY() { return posicionY; }    // Retorna coordenada Y
    public boolean isActivado() { return activado; }   // Retorna si está activado

    public void activar() {
        this.activado = true;
        System.out.println("CheckPoint " + nombre + " activado."); // Activa el checkpoint
    }
}