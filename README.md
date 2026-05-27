# README.md

# Proyecto: Batalla Pokémon en JavaFX

# Autores
Maldonado Maldujano Ramiro
Vazquez Martinez Ivan Alonso
Zavala Bupunari Christian Efrain

## Descripción

Este proyecto es una simulación sencilla de una batalla Pokémon desarrollada en **Java** utilizando **JavaFX** para la interfaz gráfica.

El jugador controla a **Houndoom** y combate contra un **Pidgeot salvaje** en una pantalla estilo Pokémon clásico. 
El sistema incluye ataques, barra de vida, uso de pociones, turnos automáticos del rival y múltiples clases orientadas 
a objetos para representar personajes, objetos e interacción dentro del juego.

## Características del Proyecto

* Interfaz gráfica desarrollada con JavaFX
* Dos Pokémon enfrentándose en pantalla
* Fondo de batalla personalizado
* Barras de vida dinámicas
* Sistema de turnos
* Ataques con daño calculado automáticamente
* Uso de pociones para curación
* Inteligencia artificial básica del rival
* Sistema de inventario
* Clases auxiliares (obstáculos, checkpoints, recompensas, utilería)

## Estructura de Clases

### Clases Principales

| Clase              | Descripción                              |
| ------------------ | ---------------------------------------- |
| `HelloApplication` | Ventana principal del juego en JavaFX    |
| `Main`             | Versión del juego en consola             |
| `Pokemon`          | Representa un Pokémon jugable o enemigo  |
| `Personaje`        | Clase base con vida, nombre y movimiento |
| `Movimiento`       | Ataques disponibles                      |
| `Recompensa`       | Objetos curativos o premios              |
| `Inventario`       | Almacena objetos inventariables          |
| `Nivel`            | Gestiona obstáculos y checkpoints        |
| `Obstaculo`        | Elementos dañinos o destruibles          |
| `CheckPoint`       | Punto de control                         |
| `Arma`             | Objeto ofensivo                          |
| `Utileria`         | Objetos móviles o de uso especial        |


## Sistema de Combate

### Turno del Jugador

El jugador puede elegir entre:

1. Ataque 1
2. Ataque 2
3. Usar poción

### Turno del Rival

El rival realiza acciones automáticas:

* Atacar aleatoriamente
* Curarse si tiene poca vida

### Final del Combate

Gana quien reduzca la vida del rival a 0.


## Recursos Necesarios

Las imágenes deben estar dentro de:

```plaintext
src/main/resources/images/
```

### Archivos requeridos:

```plaintext
Fondo.png
houndoom.png
pidgeot.png
```

---

## Cómo Ejecutar

## Requisitos

* Java JDK 21 o superior
* Maven instalado
* IntelliJ IDEA recomendado

## Ejecutar desde terminal

```bash
mvn clean javafx:run
```

## Ejecutar desde IntelliJ

1. Abrir proyecto Maven
2. Esperar descarga de dependencias
3. Ejecutar `HelloApplication.java`

---

## Ejemplo de Juego

```text
¡Un Pidgeot salvaje apareció!

Houndoom usa Lanzallamas
Pidgeot recibió daño

Pidgeot usa Vendaval
Houndoom recibió daño
```

## Programación Orientada a Objetos Aplicada

Este proyecto utiliza:

* Herencia
* Polimorfismo
* Interfaces
* Encapsulamiento
* Composición
* Sobreescritura de métodos

## Interfaces Utilizadas

### `Destruible`

```java
void destruye();
```

### `ElementoDinamico`

```java
void mover(String direccion, int distancia);
```

### `Inventariable`

```java
void registrar();
void borrar();
```

##  Mecánicas Implementadas

* Cálculo de daño
* Curación
* Barras de vida con colores
* Turnos automáticos
* Botones de ataque
* Mensajes en pantalla

## Mejoras Futuras

* Animaciones de ataques
* Música de fondo
* Sistema de experiencia
* Guardado de partida

## Tecnologías Utilizadas

* Java
* JavaFX
* Maven
