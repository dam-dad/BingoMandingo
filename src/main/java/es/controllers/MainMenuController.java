package es.controllers;

import es.App;
import es.MetodosImpresion;
import es.controllers.Models.BingoGenerator;
import es.controllers.Models.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

import javax.print.PrintService;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    @FXML
    private BorderPane root;

    @FXML
    private Button gameButton;

    @FXML
    private Button scoreButton;

    private GameController gc = new GameController();
    private static ListView<Player> listaJugadores = new ListView<>();


    public MainMenuController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainMenuView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onGameViewAction(ActionEvent event) {
        // Crear el alert de tipo Dialog con botones personalizados
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Configurar jugadores");
        alert.setHeaderText("Introduce los datos de los jugadores");
        alert.setResizable(true);

        // Contenido personalizado
        VBox content = new VBox(10);
        content.setPadding(new Insets(10));

        Label instruccionesLabel = new Label("Introduce el nombre y apellido de los jugadores:");
        TextField nombreField = new TextField();
        nombreField.setPromptText("Nombre");
        TextField apellidoField = new TextField();
        apellidoField.setPromptText("Apellido");

        // Botón para agregar jugadores
        Button agregarJugadorButton = new Button("Añadir jugador");
        agregarJugadorButton.setOnAction(e -> {
            String nombre = nombreField.getText().trim();
            String apellido = apellidoField.getText().trim();

            if (!nombre.isEmpty() && !apellido.isEmpty()) {
                // Crear un nuevo jugador con datos por defecto
                Player jugador = new Player(0, nombre, apellido, 0);
                listaJugadores.getItems().add(jugador);
                nombreField.clear();
                apellidoField.clear();
            } else {
                Alert alertaError = new Alert(Alert.AlertType.WARNING);
                alertaError.setHeaderText(null);
                alertaError.setContentText("El nombre y apellido no pueden estar vacíos.");
                alertaError.showAndWait();
            }
        });

        content.getChildren().addAll(instruccionesLabel, listaJugadores, nombreField, apellidoField, agregarJugadorButton);
        alert.getDialogPane().setContent(content);

        // Botones del alert
        ButtonType botonContinuar = new ButtonType("Continuar", ButtonBar.ButtonData.OK_DONE);
        ButtonType botonCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(botonContinuar, botonCancelar);

        // Manejar el resultado del diálogo
        alert.showAndWait().ifPresent(response -> {
            if (response == botonContinuar) {
                if (listaJugadores.getItems().isEmpty()) {
                    Alert alertaError = new Alert(Alert.AlertType.WARNING);
                    alertaError.setHeaderText(null);
                    alertaError.setContentText("Debes añadir al menos un jugador antes de continuar.");
                    alertaError.showAndWait();
                } else {
                    try{
                        BingoGenerator bingoGenerator = new BingoGenerator(Objects.requireNonNull(getClass().getResource("/resources/TablasBingo.xlsx")).toURI().getPath());

                        //limpiar excel antes de escribir
                        bingoGenerator.limpiarExcel();

                        //generar cartones
                        int numCartones = listaJugadores.getItems().size(); //obtener cantidad jugadores
                        bingoGenerator.generarCartones(numCartones);
                        bingoGenerator.guardarCartonesEnExcel();


                        //imprimir cartones
                        MetodosImpresion.convertXlsxToPdf(Objects.requireNonNull(getClass().getResource("/resources/TablasBingo.xlsx")).toURI().getPath(), Objects.requireNonNull(getClass().getResource("/resources/TablasBingo.pdf")).toURI().getPath());
                        PrintService printer = MetodosImpresion.choosePrinter();
                        if (printer != null) {
                            MetodosImpresion.imprimir(Objects.requireNonNull(getClass().getResource("/resources/TablasBingo.pdf")).toURI().getPath(), printer);
                        }

                        //asignar cartones a cada jugador
                        for (int i = 0; i< listaJugadores.getItems().size(); i++) {

                             Player player = listaJugadores.getItems().get(i);
                             player.setCarton(bingoGenerator.getCartones().get(i));
                            System.out.println("Jugador: " + player.getNombre() + " " + player.getApellido() + " - Cartón asignado: " + player.getCarton().toString());

                        }
                        //Pasamos la lista de jugadores al controlador del juego
                        gc.setJugadores(listaJugadores.getItems());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (PrinterException e) {
                        throw new RuntimeException(e);
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                    // Procesar los jugadores y avanzar
                    listaJugadores.getItems().forEach(jugador -> {
                        System.out.println("Jugador añadido: " + jugador);
                        // Aquí puedes almacenar los jugadores en una lista global o pasarla al controlador del juego
                    });

                    App.getRc().getRoot().setCenter(gc.getRoot()); // Cambiar a la vista del juego
                }
            }
        });
    }

    public static ListView<Player> getListaJugadores() {
        return listaJugadores;
    }
}