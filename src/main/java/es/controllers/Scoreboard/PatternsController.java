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

public class PatternsController implements Initializable {


    @FXML
    private BorderPane root;

    @FXML
    private TabPane patternPane;

    @FXML
    private Tab formTab;

    @FXML
    private Tab letterTab;

    @FXML
    private Tab numberTab;

    public PatternsController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PatternsView.fxml"));
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
}
