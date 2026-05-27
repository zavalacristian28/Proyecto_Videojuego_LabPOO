package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Pokemon extends Personaje {

    private int puntosAtaque;
    private int puntosDefensa;

    private List<Movimiento> movimientos;

    public Pokemon(String nombre,
                   int vida,
                   int posX,
                   int posY,
                   int ataque,
                   int defensa) {

        super(nombre, vida, posX, posY);

        this.puntosAtaque = ataque;
        this.puntosDefensa = defensa;

        movimientos = new ArrayList<>();
    }

    public void aprenderMovimiento(Movimiento movimiento) {

        if (movimientos.size() < 4) {
            movimientos.add(movimiento);
        }
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public int getPuntosDefensa() {
        return puntosDefensa;
    }

    public void atacar(int indiceMovimiento,
                       Pokemon objetivo) {

        if (indiceMovimiento < 0
                || indiceMovimiento >= movimientos.size()) {
            return;
        }

        Movimiento movimiento =
                movimientos.get(indiceMovimiento);

        int dano = calcularDano(
                movimiento,
                objetivo
        );

        System.out.println(
                getNombre()
                        + " usa "
                        + movimiento.getNombre()
        );

        objetivo.recibirDaño(dano);
    }

    private int calcularDano(Movimiento movimiento,
                             Pokemon objetivo) {

        int dano =
                (puntosAtaque
                        + movimiento.getPotencia())
                        - (objetivo.getPuntosDefensa() / 2);

        if (dano < 10) {
            dano = 10;
        }

        return dano;
    }

    public void usarPocion(Recompensa pocion) {

        curar(pocion.getValor());
    }
}
