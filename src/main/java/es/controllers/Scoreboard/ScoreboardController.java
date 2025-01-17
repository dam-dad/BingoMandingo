package es.controllers.Scoreboard;

import es.controllers.Models.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private TableView<Player> puntuacionTable;

    @FXML
    private TableColumn<Player, String> apellidojugadorColumn;

    @FXML
    private TableColumn<Player, Integer> idcartonColumn;

    @FXML
    private TableColumn<Player, String> nombrejugadorColumn;

    @FXML
    private TableColumn<Player, Integer> puntuacionjugadorColumn;

    private final ObservableList<Player> playerList = FXCollections.observableArrayList();


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

        idcartonColumn.setCellValueFactory(cellData -> cellData.getValue().idCartonProperty().asObject());
        nombrejugadorColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        apellidojugadorColumn.setCellValueFactory(cellData -> cellData.getValue().apellidoProperty());
        puntuacionjugadorColumn.setCellValueFactory(cellData -> cellData.getValue().puntuacionProperty().asObject());

        puntuacionTable.setItems(playerList);

        Player jugadorEjemplo = new Player(1, "Juan", "Pérez", 100);
        addPlayer(jugadorEjemplo);

    }

    public void addPlayer(Player player) {
        playerList.add(player);
    }

    public BorderPane getRoot() {
        return root;
    }
}
