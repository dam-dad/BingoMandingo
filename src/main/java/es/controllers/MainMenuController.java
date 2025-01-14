package es.controllers;

import es.App;

import es.controllers.Scoreboard.NavMenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    @FXML
    private BorderPane root;

    @FXML
    private Button gameButton;

    @FXML
    private Button scoreButton;

    private GameController gc = new GameController();
    private NavMenuController nc = new NavMenuController();

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
        App.getRc().getRoot().setCenter(gc.getRoot());
    }

    @FXML
    private void onScoreAction() {
        App.getRc().getRoot().setCenter(nc.getRoot());
    }
}
