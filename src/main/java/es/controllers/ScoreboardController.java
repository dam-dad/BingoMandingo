package es.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ScoreboardController implements Initializable {

    @FXML
    private BorderPane root;

    @FXML
    private Label puntuacionLabel;

    @FXML
    private TableView<?> puntuacionTable;

    @FXML
    private TableColumn<?, ?> apellidojugadorColum;

    @FXML
    private TableColumn<?, ?> idcartonColumn;

    @FXML
    private TableColumn<?, ?> nombrejugadorColumn;

    @FXML
    private TableColumn<?, ?> puntuacionjugadorColumn;

    public ScoreboardController(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ScoreboardView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public BorderPane getRoot() {
        return root;
    }
}
