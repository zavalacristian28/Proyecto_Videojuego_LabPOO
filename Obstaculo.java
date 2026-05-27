package com.example.demo;

public class Obstaculo implements Destruible {
    private String nombre;     // Nombre del obstáculo
    private int dano;          // Daño que causa al colisionar
    private int posicionX;     // Coordenada X en el nivel
    private int posicionY;     // Coordenada Y en el nivel
    private boolean destruido; // Indicado en el diagrama detallado

    // Constructor
    public Obstaculo(String nombre, int dano, int posicionX, int posicionY) {
        this.nombre = nombre;
        this.dano = dano;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.destruido = false;
    }

    public String getNombre() { return nombre; }       // Retorna el nombre
    public int getDano() { return dano; }              // Retorna el daño
    public int getPosicionX() { return posicionX; }    // Retorna coordenada X
    public int getPosicionY() { return posicionY; }    // Retorna coordenada Y
    public boolean isDestruido() { return destruido; } // Indicado en el diagrama detallado

    @Override
    public void destruye() {
        this.destruido = true;
        System.out.println("Obstáculo " + nombre + " destruido."); // Implementación de Destruible
    }
}