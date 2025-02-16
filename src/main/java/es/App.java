package es;

import es.controllers.RootController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static RootController rc;

    @Override
    public void start(Stage primaryStage) throws Exception {

        rc = new RootController();

        primaryStage.setTitle("Bingo Mandingo");
        Scene scene = new Scene(rc.getRoot(), 600, 400);
        primaryStage.setScene(scene);
        scene.getStylesheets().add("/css/styles.css");
        primaryStage.show();

    }

    public static RootController getRc() {
        return rc;
    }
}
