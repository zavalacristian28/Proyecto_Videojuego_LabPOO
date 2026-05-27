package com.example.demo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Canvas {
    private static Canvas canvas;
    private BufferedImage imagen;
    private Graphics2D graphics;
    private Map<String, Shape> figuras;

    private Canvas() {
        imagen = new BufferedImage(500, 300, BufferedImage.TYPE_INT_ARGB);
        graphics = imagen.createGraphics();
        figuras = new HashMap<>();
    }

    public static Canvas getCanvas() {
        if (canvas == null) {
            canvas = new Canvas();
        }
        return canvas;
    }

    public Graphics2D getGraphics() {
        return graphics;
    }

    public void draw(String nombre, String color, Shape figura) {
        figuras.put(nombre, figura);
        graphics.setColor(convertirColor(color));
        graphics.fill(figura);
    }

    private Color convertirColor(String color) {
        if ("black".equalsIgnoreCase(color)) return Color.BLACK;
        if ("white".equalsIgnoreCase(color)) return Color.WHITE;
        if ("green".equalsIgnoreCase(color)) return Color.GREEN;
        return Color.GRAY;
    }
}
