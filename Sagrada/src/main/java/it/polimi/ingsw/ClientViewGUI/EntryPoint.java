package it.polimi.ingsw.ClientViewGUI;

import it.polimi.ingsw.ClientView.*;
import it.polimi.ingsw.NetworkClient.SocketController;
import it.polimi.ingsw.ServerController.ClientHandlerInterface;
import it.polimi.ingsw.model.Observable;
import it.polimi.ingsw.model.PlayerPackage.State;
import it.polimi.ingsw.model.UsersList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;


public class EntryPoint extends Application implements Observer {
    static Stage stage;
    private static EntryPoint instance;
    private ClientHandlerInterface serverController;
    private SocketController socketController;

    public SocketController getSocketController(){
        return this.socketController;
    }
    public void setSocketController(SocketController socketController) {
        this.socketController = socketController;
    }
    public ClientHandlerInterface getServerController(){
        return this.serverController;
    }
    public void setServerController(ClientHandlerInterface serverController){
        this.serverController=serverController;
    }
    public static EntryPoint Singleton() {
        if (instance == null) {
            instance = new EntryPoint();
        }
        return instance;
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        /*FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/LogIn.fxml"));
        Parent root = loader.load();
        ((LogInController) loader.getController()).setPrimaryStage(primaryStage);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
        */
        FXMLLoader loader = new FXMLLoader();                //Code to test faster the game interface
        loader.setLocation(getClass().getResource("/MainGameView.fxml"));
        Parent root = loader.load();
        ((MainGameViewController) loader.getController()).setPrimaryStage(primaryStage);

        Scene scene = new Scene(root);
        primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
        primaryStage.show();
        }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public synchronized void update(Observable o, Object arg) throws RemoteException{
        State state =  o.getState();
        switch (state){
            case ERRORSTATE: {
                break;
            }
            case HASSETADICESTATE: {
                break;
            }
            case HASUSEDATOOLCARDACTIONSTATE: {
                break;
            }
            case MATCHNOTSTARTEDYETSTATE: {
                break;
            }
            case MUSTPASSTURNSTATE: {
                break;
            }
            case MUSTSSETDILUENTEPERPASTASALDASTATE: {
                break;
            }
            case MUSTSETPENNELLOPERPASTASALDASTATE: {
                break;
            }
            case NOTYOURTURNSTATE: {
                break;
            }
            case STARTTURNSTATE: {
                break;
            }
            case ENDMATCHSTATE: {
                break;
            }
            case MUSTSETSCHEMECARD: {
                break;
            }
        }
    }
}
