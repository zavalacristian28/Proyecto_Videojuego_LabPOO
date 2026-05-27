package com.example.demo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorArchivos {


    public static void guardarPartida(String nombreArchivo, Pokemon jugador, Pokemon rival) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(nombreArchivo));

            // Guardamos usando el formato: Elemento Nombre Vida
            writer.write("Pokemon " + jugador.getNombre() + " " + jugador.getVida());
            writer.newLine();
            writer.write("Pokemon " + rival.getNombre() + " " + rival.getVida());
            writer.newLine();

            System.out.println("Partida guardada correctamente en: " + nombreArchivo);

        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.err.println("Error al cerrar el archivo: " + e.getMessage());
                }
            }
        }
    }


    public static List<String> cargarPartida(String nombreArchivo) {
        List<String> configuracion = new ArrayList<>();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(nombreArchivo));
            String linea;

            while ((linea = reader.readLine()) != null) {
                configuracion.add(linea);
            }
            System.out.println("Configuración leída del archivo: " + nombreArchivo);

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.err.println("Error al cerrar el archivo: " + e.getMessage());
                }
            }
        }
        return configuracion;
    }
}