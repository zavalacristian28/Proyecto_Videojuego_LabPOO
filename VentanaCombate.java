package com.example.demo;

import java.awt.*;
import java.awt.geom.*;

public class VentanaCombate {

    private Canvas canvas;

    public VentanaCombate() {

        canvas = Canvas.getCanvas();

        dibujarEscenario();
        dibujarPokemon();
        dibujarCajasInfo();

        escribirInfo("Pidgeot",160,270,40);
        escribirInfo("Houndoom",150,50,160);

        barraVida(160,270,60);
        barraVida(150,50,180);
    }

    private void dibujarEscenario(){
        Rectangle fondo = new Rectangle(0,0,500,300);
        canvas.draw("fondo","green",fondo);

        Rectangle cajaTexto = new Rectangle(0,220,500,80);
        canvas.draw("cajaTexto","white",cajaTexto);
    }

    private void dibujarPokemon(){

        Graphics2D g = canvas.getGraphics();

        Image houndoom = Toolkit.getDefaultToolkit().getImage("C:/Users/Zavala/IdeaProjects/VideoJuego1/src/main/java/resources/Imagenes/houndoom.png");
        Image pidgeot = Toolkit.getDefaultToolkit().getImage("C:/Users/Zavala/IdeaProjects/VideoJuego1/src/main/java/resources/Imagenes/pidgeot.png");

        g.drawImage(pidgeot,300,50,120,120,null);
        g.drawImage(houndoom,80,120,120,120,null);
    }

    private void dibujarCajasInfo(){

        Rectangle infoEnemigo = new Rectangle(260,20,200,60);
        canvas.draw("infoE","white",infoEnemigo);

        Rectangle infoJugador = new Rectangle(40,140,200,60);
        canvas.draw("infoJ","white",infoJugador);
    }

    private void escribirInfo(String nombre, int vida, int x, int y){

        Graphics2D g = canvas.getGraphics();

        g.setColor(Color.black);
        g.setFont(new Font("Arial",Font.BOLD,14));

        g.drawString(nombre,x,y);
        g.drawString("HP: "+vida,x,y+20);
    }

    private void barraVida(int vida, int x, int y){

        Rectangle fondo = new Rectangle(x,y,100,10);
        canvas.draw("hpbg"+x,"black",fondo);

        Rectangle vidaBar = new Rectangle(x,y,vida/2,10);
        canvas.draw("hp"+x,"green",vidaBar);
    }
}
