package it.polimi.ingsw.ClientViewGUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EntryPoint extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
         FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/LogIn.fxml"));
        Parent root = loader.load();
        ((LogInController) loader.getController()).setPrimaryStage(primaryStage);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

