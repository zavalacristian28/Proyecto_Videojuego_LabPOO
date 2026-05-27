package com.example.demo;
import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private int capacidadMaxima;        // Número máximo de items que puede contener
    private List<Inventariable> items;  // Lista de objetos inventariables

    // Constructor
    public Inventario(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
        this.items = new ArrayList<>();
    }

    public int getCapacidadMaxima() { return capacidadMaxima; } // Retorna la capacidad máxima
    public List<Inventariable> getItems() { return items; }     // Retorna la lista de items

    public boolean agregarItem(Inventariable item) {
        if (items.size() < capacidadMaxima) {
            items.add(item);
            item.registrar();
            return true; // Agrega un item, retorna true si fue exitoso
        }
        return false;
    }

    public boolean eliminarItem(Inventariable item) {
        if (items.remove(item)) {
            item.borrar();
            return true; // Elimina un item, retorna true si fue exitoso
        }
        return false;
    }

    public void listarItems() {
        System.out.println("--- Objetos en Inventario ---");
        for (Inventariable item : items) {
            System.out.println("- " + item.getClass().getSimpleName()); // Muestra todos los items en consola
        }
    }
}
