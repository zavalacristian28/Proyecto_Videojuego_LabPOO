package com.example.demo;

public class Personaje implements Destruible, ElementoDinamico {
    private String nombre;
    protected int vida; // Cambiado a protected para que Pokemon lo use fácilmente
    private int posicionX;
    private int posicionY;

    public Personaje(String nombre, int vida, int posicionX, int posicionY) {
        this.nombre = nombre;
        this.vida = vida;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }

    public String getNombre() { return nombre; }
    public int getVida() { return vida; }

    // NUEVO MÉTODO AÑADIDO PARA LA PRÁCTICA 10
    public void setVida(int vida) {
        this.vida = vida;
    }
    public void recibirDaño(int dano) {
        this.vida -= dano;
        if (this.vida < 0) this.vida = 0;
        System.out.println(nombre + " recibió " + dano + " de daño. Vida restante: " + vida);
    }

    public void curar(int cantidad) {
        this.vida += cantidad;
        System.out.println(nombre + " recuperó " + cantidad + " de vida. Vida actual: " + vida);
    }

    @Override
    public void mover(String direccion, int distancia) {
        System.out.println(nombre + " se posiciona para atacar.");
    }

    @Override
    public void destruye() {
        System.out.println("¡" + nombre + " ya no puede luchar!");
    }
}