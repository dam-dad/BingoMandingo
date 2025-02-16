package es.controllers;

import es.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class GameController implements Initializable {

    @FXML
    private BorderPane root;

    @FXML
    private Label bolaTabla1;

    @FXML
    private Label bolaTabla10;

    @FXML
    private Label bolaTabla11;

    @FXML
    private Label bolaTabla12;

    @FXML
    private Label bolaTabla13;

    @FXML
    private Label bolaTabla14;

    @FXML
    private Label bolaTabla15;

    @FXML
    private Label bolaTabla16;

    @FXML
    private Label bolaTabla17;

    @FXML
    private Label bolaTabla18;

    @FXML
    private Label bolaTabla19;

    @FXML
    private Label bolaTabla2;

    @FXML
    private Label bolaTabla20;

    @FXML
    private Label bolaTabla21;

    @FXML
    private Label bolaTabla22;

    @FXML
    private Label bolaTabla23;

    @FXML
    private Label bolaTabla24;

    @FXML
    private Label bolaTabla25;

    @FXML
    private Label bolaTabla26;

    @FXML
    private Label bolaTabla27;

    @FXML
    private Label bolaTabla28;

    @FXML
    private Label bolaTabla29;

    @FXML
    private Label bolaTabla3;

    @FXML
    private Label bolaTabla30;

    @FXML
    private Label bolaTabla31;

    @FXML
    private Label bolaTabla32;

    @FXML
    private Label bolaTabla33;

    @FXML
    private Label bolaTabla34;

    @FXML
    private Label bolaTabla35;

    @FXML
    private Label bolaTabla36;

    @FXML
    private Label bolaTabla37;

    @FXML
    private Label bolaTabla38;

    @FXML
    private Label bolaTabla39;

    @FXML
    private Label bolaTabla4;

    @FXML
    private Label bolaTabla40;

    @FXML
    private Label bolaTabla41;

    @FXML
    private Label bolaTabla42;

    @FXML
    private Label bolaTabla43;

    @FXML
    private Label bolaTabla44;

    @FXML
    private Label bolaTabla45;

    @FXML
    private Label bolaTabla46;

    @FXML
    private Label bolaTabla47;

    @FXML
    private Label bolaTabla48;

    @FXML
    private Label bolaTabla49;

    @FXML
    private Label bolaTabla5;

    @FXML
    private Label bolaTabla50;

    @FXML
    private Label bolaTabla51;

    @FXML
    private Label bolaTabla52;

    @FXML
    private Label bolaTabla53;

    @FXML
    private Label bolaTabla54;

    @FXML
    private Label bolaTabla55;

    @FXML
    private Label bolaTabla56;

    @FXML
    private Label bolaTabla57;

    @FXML
    private Label bolaTabla58;

    @FXML
    private Label bolaTabla59;

    @FXML
    private Label bolaTabla6;

    @FXML
    private Label bolaTabla60;

    @FXML
    private Label bolaTabla61;

    @FXML
    private Label bolaTabla62;

    @FXML
    private Label bolaTabla63;

    @FXML
    private Label bolaTabla64;

    @FXML
    private Label bolaTabla65;

    @FXML
    private Label bolaTabla66;

    @FXML
    private Label bolaTabla67;

    @FXML
    private Label bolaTabla68;

    @FXML
    private Label bolaTabla69;

    @FXML
    private Label bolaTabla7;

    @FXML
    private Label bolaTabla70;

    @FXML
    private Label bolaTabla71;

    @FXML
    private Label bolaTabla72;

    @FXML
    private Label bolaTabla73;

    @FXML
    private Label bolaTabla74;

    @FXML
    private Label bolaTabla75;

    @FXML
    private Label bolaTabla76;

    @FXML
    private Label bolaTabla77;

    @FXML
    private Label bolaTabla78;

    @FXML
    private Label bolaTabla79;

    @FXML
    private Label bolaTabla8;

    @FXML
    private Label bolaTabla80;

    @FXML
    private Label bolaTabla81;

    @FXML
    private Label bolaTabla82;

    @FXML
    private Label bolaTabla83;

    @FXML
    private Label bolaTabla84;

    @FXML
    private Label bolaTabla85;

    @FXML
    private Label bolaTabla86;

    @FXML
    private Label bolaTabla87;

    @FXML
    private Label bolaTabla88;

    @FXML
    private Label bolaTabla89;

    @FXML
    private Label bolaTabla9;

    @FXML
    private Label bolaTabla90;

    @FXML
    private Label num1HistLabel;

    @FXML
    private Label num2HistLabel;

    @FXML
    private Label num3HistLabel;

    @FXML
    private Label num4HistLabel;

    @FXML
    private Label num5HistLabel;

    @FXML
    private Label numeroActual;

    @FXML
    private ImageView imagebola1;

    //Generar numeros aleatorios
    private List<Integer> numerosGenerados = new ArrayList<>();
    private Random random = new Random();

    private Queue<Integer> historialNumeros;

    public GameController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GameView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        historialNumeros = new LinkedList<>();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    private void actualizarNumeros() {
        // Generar un número aleatorio
        // Números del 1 al 90
        int nuevoNumero = generarNumeroUnico();

        // Actualizar las etiquetas del historial
        num5HistLabel.setText(num4HistLabel.getText());
        num4HistLabel.setText(num3HistLabel.getText());
        num3HistLabel.setText(num2HistLabel.getText());
        num2HistLabel.setText(num1HistLabel.getText());
        num1HistLabel.setText(numeroActual.getText());

        // Mostrar el nuevo número en la etiqueta actual
        numeroActual.setText(String.valueOf(nuevoNumero));

        // Cambiar la imagen de la bola correspondiente
        cambiarImagenBola(nuevoNumero);
    }

    private int generarNumeroUnico() {
        if (numerosGenerados.size() >= 90) {
            throw new IllegalStateException("Todos los números del 1 al 90 ya han sido generados.");
        }

        int numero;
        do {
            numero = random.nextInt(90) + 1;
        } while (numerosGenerados.contains(numero));

        numerosGenerados.add(numero);
        return numero;
    }

    public void agregarNumeroAlHistorial(int numero) {
        historialNumeros.add(numero);
    }

    public Queue<Integer> obtenerHistorialNumeros() {
        return new LinkedList<>(historialNumeros);
    }

    @FXML
    void onResumeAction(ActionEvent event) {
        actualizarNumeros();
    }

    @FXML
    void onBackButtonAction(ActionEvent event) {
        App.getRc().getRoot().setCenter(App.getRc().getMmc().getRoot());
    }

    private void cambiarImagenBola(int numero) {
        Label label = null;
        switch (numero) {
            case 1:
                label = bolaTabla1;
                break;
            case 2:
                label = bolaTabla2;
                break;
            case 3:
                label = bolaTabla3;
                break;
            case 4:
                label = bolaTabla4;
                break;
            case 5:
                label = bolaTabla5;
                break;
            case 6:
                label = bolaTabla6;
                break;
            case 7:
                label = bolaTabla7;
                break;
            case 8:
                label = bolaTabla8;
                break;
            case 9:
                label = bolaTabla9;
                break;
            case 10:
                label = bolaTabla10;
                break;
            case 11:
                label = bolaTabla11;
                break;
            case 12:
                label = bolaTabla12;
                break;
            case 13:
                label = bolaTabla13;
                break;
            case 14:
                label = bolaTabla14;
                break;
            case 15:
                label = bolaTabla15;
                break;
            case 16:
                label = bolaTabla16;
                break;
            case 17:
                label = bolaTabla17;
                break;
            case 18:
                label = bolaTabla18;
                break;
            case 19:
                label = bolaTabla19;
                break;
            case 20:
                label = bolaTabla20;
                break;
            case 21:
                label = bolaTabla21;
                break;
            case 22:
                label = bolaTabla22;
                break;
            case 23:
                label = bolaTabla23;
                break;
            case 24:
                label = bolaTabla24;
                break;
            case 25:
                label = bolaTabla25;
                break;
            case 26:
                label = bolaTabla26;
                break;
            case 27:
                label = bolaTabla27;
                break;
            case 28:
                label = bolaTabla28;
                break;
            case 29:
                label = bolaTabla29;
                break;
            case 30:
                label = bolaTabla30;
                break;
            case 31:
                label = bolaTabla31;
                break;
            case 32:
                label = bolaTabla32;
                break;
            case 33:
                label = bolaTabla33;
                break;
            case 34:
                label = bolaTabla34;
                break;
            case 35:
                label = bolaTabla35;
                break;
            case 36:
                label = bolaTabla36;
                break;
            case 37:
                label = bolaTabla37;
                break;
            case 38:
                label = bolaTabla38;
                break;
            case 39:
                label = bolaTabla39;
                break;
            case 40:
                label = bolaTabla40;
                break;
            case 41:
                label = bolaTabla41;
                break;
            case 42:
                label = bolaTabla42;
                break;
            case 43:
                label = bolaTabla43;
                break;
            case 44:
                label = bolaTabla44;
                break;
            case 45:
                label = bolaTabla45;
                break;
            case 46:
                label = bolaTabla46;
                break;
            case 47:
                label = bolaTabla47;
                break;
            case 48:
                label = bolaTabla48;
                break;
            case 49:
                label = bolaTabla49;
                break;
            case 50:
                label = bolaTabla50;
                break;
            case 51:
                label = bolaTabla51;
                break;
            case 52:
                label = bolaTabla52;
                break;
            case 53:
                label = bolaTabla53;
                break;
            case 54:
                label = bolaTabla54;
                break;
            case 55:
                label = bolaTabla55;
                break;
            case 56:
                label = bolaTabla56;
                break;
            case 57:
                label = bolaTabla57;
                break;
            case 58:
                label = bolaTabla58;
                break;
            case 59:
                label = bolaTabla59;
                break;
            case 60:
                label = bolaTabla60;
                break;
            case 61:
                label = bolaTabla61;
                break;
            case 62:
                label = bolaTabla62;
                break;
            case 63:
                label = bolaTabla63;
                break;
            case 64:
                label = bolaTabla64;
                break;
            case 65:
                label = bolaTabla65;
                break;
            case 66:
                label = bolaTabla66;
                break;
            case 67:
                label = bolaTabla67;
                break;
            case 68:
                label = bolaTabla68;
                break;
            case 69:
                label = bolaTabla69;
                break;
            case 70:
                label = bolaTabla70;
                break;
            case 71:
                label = bolaTabla71;
                break;
            case 72:
                label = bolaTabla72;
                break;
            case 73:
                label = bolaTabla73;
                break;
            case 74:
                label = bolaTabla74;
                break;
            case 75:
                label = bolaTabla75;
                break;
            case 76:
                label = bolaTabla76;
                break;
            case 77:
                label = bolaTabla77;
                break;
            case 78:
                label = bolaTabla78;
                break;
            case 79:
                label = bolaTabla79;
                break;
            case 80:
                label = bolaTabla80;
                break;
            case 81:
                label = bolaTabla81;
                break;
            case 82:
                label = bolaTabla82;
                break;
            case 83:
                label = bolaTabla83;
                break;
            case 84:
                label = bolaTabla84;
                break;
            case 85:
                label = bolaTabla85;
                break;
            case 86:
                label = bolaTabla86;
                break;
            case 87:
                label = bolaTabla87;
                break;
            case 88:
                label = bolaTabla88;
                break;
            case 89:
                label = bolaTabla89;
                break;
            case 90:
                label = bolaTabla90;
                break;
        }
        if (label != null) {
            ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/images/contenedorNumeroTachado.png")));
            imageView.setFitHeight(73.0);
            imageView.setFitWidth(38.0);
            imageView.setPickOnBounds(true);
            imageView.setPreserveRatio(true);
            label.setGraphic(imageView);
        }
    }

    @FXML
    void onComprobarBingoAction(ActionEvent event) {

    }

    @FXML
    void onComprobarLineaAction(ActionEvent event) {

    }
}