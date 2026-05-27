package com.example.demo;

public class Utileria implements ElementoDinamico {
    private String nombre;      // Nombre de la utilería
    private String descripcion; // Descripción del objeto
    private int posicionX;      // Coordenada X en el nivel
    private int posicionY;      // Coordenada Y en el nivel
    private boolean usada;      // Indicado en el diagrama detallado

    // Constructor
    public Utileria(String nombre, String descripcion, int posicionX, int posicionY) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.usada = false;
    }

    public String getNombre() { return nombre; }           // Retorna el nombre
    public String getDescripcion() { return descripcion; } // Retorna la descripción
    public int getPosicionX() { return posicionX; }        // Retorna coordenada X
    public int getPosicionY() { return posicionY; }        // Retorna coordenada Y
    public boolean isUsada() { return usada; }             // Indicado en el diagrama detallado

    public void usar() {
        this.usada = true;
        System.out.println("Utilería " + nombre + " usada."); // Ejecuta la acción del objeto
    }

    @Override
    public void mover(String direccion, int distancia) {
        // Implementación de ElementoDinamico - desplaza la utilería según dirección y distancia
        System.out.println("Utilería " + nombre + " se mueve al " + direccion + " " + distancia + " unidades.");
    }
}