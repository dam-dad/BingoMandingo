package es.controllers.Scoreboard;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NavMenuController implements Initializable {

    private ScoreboardController sc = new ScoreboardController();
    private PatternsController pc = new PatternsController();

    @FXML
    private BorderPane root;

    @FXML
    private TabPane patternPane;

    @FXML
    private Tab patternTab;

    @FXML
    private Tab scoreTab;

    public NavMenuController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/NavmenuView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Patrones
        loadPatterTab();

        //Puntuaciones
        loadScoreTab();

    }

    public BorderPane getRoot() {
        return root;
    }

    private void  loadPatterTab() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PatternsView.fxml"));
            BorderPane patternContent = loader.load();

            patternTab.setContent(patternContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadScoreTab() {
        try {
            // Cargar la vista y el controlador del scoreTab
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ScoreboardView.fxml"));
            BorderPane scoreboardContent = loader.load();

            // Establecer el contenido del scoreTab con el BorderPane cargado
            scoreTab.setContent(scoreboardContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
