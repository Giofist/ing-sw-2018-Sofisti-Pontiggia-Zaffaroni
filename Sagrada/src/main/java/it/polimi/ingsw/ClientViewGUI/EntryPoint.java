package it.polimi.ingsw.ClientViewGUI;

import it.polimi.ingsw.ClientView.*;
import it.polimi.ingsw.ServerController.ClientHandlerInterface;
import it.polimi.ingsw.model.Observable;
import it.polimi.ingsw.model.PlayerPackage.State;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class EntryPoint extends Application {
    static Stage stage;

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

/*
        FXMLLoader loader = new FXMLLoader();                //Code to test faster the game interface
        loader.setLocation(getClass().getResource("/ChooseMap.fxml"));
        Parent root = loader.load();
        ((ChooseMapController) loader.getController()).setPrimaryStage(primaryStage);

        Scene scene = new Scene(root);
        primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
        primaryStage.show();
*/
    }

    public static void main(String[] args) {
        launch(args);
    }

}

