package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.*;

public class HelloApplication extends Application {

    private static final int ANCHO_VENTANA = 600;
    private static final int ALTO_VENTANA = 415;

    private static final int VIDA_MAX_JUGADOR = 240;
    private static final int VIDA_MAX_RIVAL = 300;

    private Pokemon jugador;
    private Pokemon rival;
    private Recompensa pocion;

    private ProgressBar barHPJugador;
    private ProgressBar barHPRival;

    private Label lblMensajes;
    private Label lblVidaJugador;
    private Label lblVidaRival;

    private HBox panelBotones;
    private Button btnReiniciar;

    @Override
    public void start(Stage stage) {
        inicializarCombate();

        VBox root = crearLayoutPrincipal();

        Scene scene = new Scene(root, ANCHO_VENTANA, ALTO_VENTANA);

        stage.setTitle("PokéLab Battle Arena");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    private VBox crearLayoutPrincipal() {
        VBox root = new VBox();
        root.setStyle("-fx-background-color: #333;");

        root.getChildren().addAll(
                crearMenu(),
                crearArenaBatalla(),
                crearCajaMensajes(),
                crearPanelBotones()
        );

        return root;
    }

    private MenuBar crearMenu() {
        MenuBar menuBar = new MenuBar();

        Menu menuPartida = new Menu("Partida");

        MenuItem itemGuardar = new MenuItem("Guardar Progreso");
        itemGuardar.setOnAction(e -> guardarEstado());

        MenuItem itemCargar = new MenuItem("Cargar Anterior");
        itemCargar.setOnAction(e -> cargarEstado());

        menuPartida.getItems().addAll(itemGuardar, itemCargar);
        menuBar.getMenus().add(menuPartida);

        return menuBar;
    }

    private StackPane crearArenaBatalla() {
        StackPane arena = new StackPane();
        arena.setPrefSize(600, 260);

        cargarFondo(arena);

        HBox capaSprites = crearCapaSprites();
        HBox capaStats = crearCapaStats();

        arena.getChildren().addAll(capaSprites, capaStats);

        return arena;
    }

    private void cargarFondo(StackPane arena) {
        try {
            InputStream fondoStream = getClass().getResourceAsStream("/images/fondo.jpg");

            if (fondoStream != null) {
                Image fondo = new Image(fondoStream);

                BackgroundSize bgSize = new BackgroundSize(
                        600,
                        260,
                        false,
                        false,
                        false,
                        false
                );

                BackgroundImage bgImage = new BackgroundImage(
                        fondo,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        bgSize
                );

                arena.setBackground(new Background(bgImage));
            }

        } catch (Exception e) {
            System.out.println("No se pudo cargar fondo.png");
        }
    }

    private HBox crearCapaSprites() {
        ImageView vistaJugador = crearImagenPokemon("/images/houndoom.png");
        ImageView vistaRival = crearImagenPokemon("/images/pidgeot.png");

        HBox capaSprites = new HBox(150);
        capaSprites.setAlignment(Pos.BOTTOM_CENTER);
        capaSprites.setPadding(new Insets(0, 0, 20, 0));
        capaSprites.getChildren().addAll(vistaJugador, vistaRival);

        return capaSprites;
    }

    private ImageView crearImagenPokemon(String ruta) {
        ImageView imagen = new ImageView();
        imagen.setFitWidth(110);
        imagen.setFitHeight(110);

        try {
            InputStream stream = getClass().getResourceAsStream(ruta);

            if (stream != null) {
                imagen.setImage(new Image(stream));
            }

        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen: " + ruta);
        }

        return imagen;
    }

    private HBox crearCapaStats() {
        VBox statsJugador = crearPanelStatsJugador();
        VBox statsRival = crearPanelStatsRival();

        HBox capaStats = new HBox(180);
        capaStats.setAlignment(Pos.TOP_CENTER);
        capaStats.setPadding(new Insets(15, 0, 0, 0));
        capaStats.getChildren().addAll(statsJugador, statsRival);

        return capaStats;
    }

    private VBox crearPanelStatsJugador() {
        Label lblNombreJugador = crearLabelNombre(jugador.getNombre());

        barHPJugador = crearBarraVida();
        lblVidaJugador = crearLabelVida(jugador.getVida(), VIDA_MAX_JUGADOR);

        return crearCajaStats(lblNombreJugador, barHPJugador, lblVidaJugador);
    }

    private VBox crearPanelStatsRival() {
        Label lblNombreRival = crearLabelNombre(rival.getNombre());

        barHPRival = crearBarraVida();
        lblVidaRival = crearLabelVida(rival.getVida(), VIDA_MAX_RIVAL);

        return crearCajaStats(lblNombreRival, barHPRival, lblVidaRival);
    }

    private Label crearLabelNombre(String nombre) {
        Label label = new Label(nombre);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 11));
        return label;
    }

    private Label crearLabelVida(int vidaActual, int vidaMaxima) {
        Label label = new Label(vidaActual + " / " + vidaMaxima + " HP");
        label.setFont(Font.font("Arial", 10));
        return label;
    }

    private ProgressBar crearBarraVida() {
        ProgressBar barra = new ProgressBar(1.0);
        barra.setPrefWidth(100);
        barra.setStyle("-fx-accent: green;");
        return barra;
    }

    private VBox crearCajaStats(Label nombre, ProgressBar barra, Label vida) {
        VBox caja = new VBox(2);
        caja.setPadding(new Insets(0, 8, 0, 8));
        caja.setPrefWidth(120);
        caja.setStyle("-fx-background-color: rgba(255,255,255,0.85); -fx-background-radius: 4;");
        caja.getChildren().addAll(nombre, barra, vida);
        return caja;
    }

    private VBox crearCajaMensajes() {
        lblMensajes = new Label("¡Un Pidgeot salvaje apareció! ¿Qué ordenará " + jugador.getNombre() + "?");
        lblMensajes.setFont(Font.font("Arial", FontWeight.BOLD, 13));

        VBox caja = new VBox(lblMensajes);
        caja.setAlignment(Pos.CENTER);
        caja.setPrefHeight(60);
        caja.setStyle("-fx-background-color: #f4f4f4; -fx-border-color: #ccc; -fx-padding: 10;");

        return caja;
    }

    private HBox crearPanelBotones() {
        panelBotones = new HBox(12);
        panelBotones.setAlignment(Pos.CENTER);
        panelBotones.setPrefHeight(70);
        panelBotones.setStyle("-fx-background-color: #e0e0e0;");

        agregarBotonesAtaque();
        agregarBotonPocion();
        agregarBotonReiniciar();

        return panelBotones;
    }

    private void agregarBotonesAtaque() {
        for (int i = 0; i < jugador.getMovimientos().size(); i++) {
            Movimiento movimiento = jugador.getMovimientos().get(i);

            Button btnAtaque = new Button(movimiento.getNombre());
            btnAtaque.setStyle("-fx-font-weight: bold; -fx-padding: 8 15 8 15;");

            final int indice = i;
            btnAtaque.setOnAction(e -> procesarTurnoAtaque(indice));

            panelBotones.getChildren().add(btnAtaque);
        }
    }

    private void agregarBotonPocion() {
        Button btnPocion = new Button("Usar Poción");
        btnPocion.setStyle("-fx-background-color: #90ee90; -fx-font-weight: bold; -fx-padding: 8 15 8 15;");
        btnPocion.setOnAction(e -> procesarUsoPocion());

        panelBotones.getChildren().add(btnPocion);
    }

    private void agregarBotonReiniciar() {
        btnReiniciar = new Button("Reiniciar Combate");
        btnReiniciar.setVisible(false);
        btnReiniciar.setStyle("-fx-background-color: #ff9999; -fx-font-weight: bold; -fx-padding: 8 15 8 15;");
        btnReiniciar.setOnAction(e -> reiniciarCombate());

        panelBotones.getChildren().add(btnReiniciar);
    }

    private void inicializarCombate() {
        jugador = new Pokemon("Houndoom", VIDA_MAX_JUGADOR, 80, 40, 0, 0);
        jugador.aprenderMovimiento(new Movimiento("Lanzallamas", 50));
        jugador.aprenderMovimiento(new Movimiento("Triturar", 40));

        rival = new Pokemon("Pidgeot", VIDA_MAX_RIVAL, 75, 50, 0, 0);
        rival.aprenderMovimiento(new Movimiento("Vendaval", 55));
        rival.aprenderMovimiento(new Movimiento("Ataque Ala", 35));

        pocion = new Recompensa("Poción", 40, "Curativo");
    }

    private void procesarTurnoAtaque(int indiceMovimiento) {
        String mensajeJugador = atacarJugador(indiceMovimiento);

        if (rival.getVida() <= 0) {
            rival.destruye();
            lblMensajes.setText(mensajeJugador + "\n¡" + rival.getNombre() + " se debilitó! ¡HAS GANADO!");
            finalizarCombate();
            return;
        }

        String mensajeRival = atacarRival();

        if (jugador.getVida() <= 0) {
            jugador.destruye();
            lblMensajes.setText(mensajeJugador + "\n" + mensajeRival + "\n¡" + jugador.getNombre() + " se debilitó! Has perdido el combate.");
            finalizarCombate();
            return;
        }

        lblMensajes.setText(mensajeJugador + "\n" + mensajeRival);
        actualizarInterfaz();
    }

    private String atacarJugador(int indiceMovimiento) {
        int vidaAnterior = rival.getVida();

        jugador.atacar(indiceMovimiento, rival);

        int dano = vidaAnterior - rival.getVida();
        String nombreMovimiento = jugador.getMovimientos().get(indiceMovimiento).getNombre();

        return "¡" + jugador.getNombre() + " usó " + nombreMovimiento + " haciendo " + dano + " de daño!";
    }

    private String atacarRival() {
        int indiceAleatorio = (int) (Math.random() * rival.getMovimientos().size());
        int vidaAnterior = jugador.getVida();

        rival.atacar(indiceAleatorio, jugador);

        int dano = vidaAnterior - jugador.getVida();
        String nombreMovimiento = rival.getMovimientos().get(indiceAleatorio).getNombre();

        return "¡" + rival.getNombre() + " usó " + nombreMovimiento + " haciendo " + dano + " de daño!";
    }

    private void procesarUsoPocion() {
        if (jugador.getVida() >= VIDA_MAX_JUGADOR) {
            lblMensajes.setText("¡Tu Pokémon ya tiene toda la energía!");
            return;
        }

        jugador.usarPocion(pocion);

        String mensajePocion = "¡Usaste una Poción en " + jugador.getNombre() + "!";
        String mensajeRival = atacarRival();

        lblMensajes.setText(mensajePocion + "\n" + mensajeRival);

        if (jugador.getVida() <= 0) {
            jugador.destruye();
            lblMensajes.setText(mensajePocion + "\n" + mensajeRival + "\n¡" + jugador.getNombre() + " se debilitó! Has perdido el combate.");
            finalizarCombate();
            return;
        }

        actualizarInterfaz();
    }

    private void actualizarInterfaz() {
        lblVidaJugador.setText(jugador.getVida() + " / " + VIDA_MAX_JUGADOR + " HP");
        lblVidaRival.setText(rival.getVida() + " / " + VIDA_MAX_RIVAL + " HP");

        double porcentajeJugador = calcularPorcentajeVida(jugador.getVida(), VIDA_MAX_JUGADOR);
        double porcentajeRival = calcularPorcentajeVida(rival.getVida(), VIDA_MAX_RIVAL);

        barHPJugador.setProgress(porcentajeJugador);
        barHPRival.setProgress(porcentajeRival);

        cambiarColorBarra(barHPJugador, porcentajeJugador);
        cambiarColorBarra(barHPRival, porcentajeRival);
    }

    private double calcularPorcentajeVida(int vidaActual, int vidaMaxima) {
        if (vidaActual <= 0) {
            return 0;
        }

        return (double) vidaActual / vidaMaxima;
    }

    private void cambiarColorBarra(ProgressBar barra, double porcentaje) {
        if (porcentaje < 0.25) {
            barra.setStyle("-fx-accent: red;");
        } else if (porcentaje < 0.5) {
            barra.setStyle("-fx-accent: orange;");
        } else {
            barra.setStyle("-fx-accent: green;");
        }
    }

    private void finalizarCombate() {
        actualizarInterfaz();
        panelBotones.setDisable(true);
        btnReiniciar.setVisible(true);
        btnReiniciar.setDisable(false);
    }

    private void reiniciarCombate() {
        inicializarCombate();
        actualizarInterfaz();

        panelBotones.setDisable(false);
        btnReiniciar.setVisible(false);

        lblMensajes.setText("¡El combate se ha reiniciado! Escoge un ataque.");
    }

    private void guardarEstado() {
        GestorArchivos.guardarPartida("partida_pokemon.txt", jugador, rival);
        lblMensajes.setText("Progreso guardado correctamente.");
    }

    private void cargarEstado() {
        File archivo = new File("partida_pokemon.txt");

        if (!archivo.exists()) {
            lblMensajes.setText("No hay registros guardados.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            leerArchivoPartida(reader);

            actualizarInterfaz();
            panelBotones.setDisable(false);
            btnReiniciar.setVisible(false);

            lblMensajes.setText("¡Progreso cargado desde el archivo!");

        } catch (IOException e) {
            lblMensajes.setText("Error al leer partida: " + e.getMessage());
        }
    }

    private void leerArchivoPartida(BufferedReader reader) throws IOException {
        String linea;

        while ((linea = reader.readLine()) != null) {
            String[] partes = linea.split(" ");

            if (partes.length < 3) {
                continue;
            }

            String nombrePokemon = partes[1];
            int vida = Integer.parseInt(partes[2]);

            if (nombrePokemon.equals("Houndoom")) {
                jugador.setVida(vida);
            } else if (nombrePokemon.equals("Pidgeot")) {
                rival.setVida(vida);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}