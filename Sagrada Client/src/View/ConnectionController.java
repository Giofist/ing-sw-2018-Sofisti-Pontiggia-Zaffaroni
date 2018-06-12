package View;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ConnectionController {
    String ipAddr = "127.0.0.1";
    @FXML
    private StackPane rootPane;

    @FXML
    private JFXButton RMIButton;

    @FXML
    private JFXButton SocketButton;

    @FXML
    void SocketClientLounch(MouseEvent event) {
        System.out.println("Socket");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        Stage secondaryStage = new Stage();
        secondaryStage.initStyle(StageStyle.UNDECORATED);
        scene.setFill(Color.TRANSPARENT);
        secondaryStage.setScene(scene);
        secondaryStage.initStyle(StageStyle.TRANSPARENT);
        secondaryStage.show();
        //rootPane.getScene().getWindow().hide(); //todo chiudere finestra selezione socket/rmi
    }

    @FXML
    void rmiClientLounch(MouseEvent event) {
        try {
        Registry rmiRegistry = LocateRegistry.getRegistry(ipAddr);
        //ClientHandlerInterface controller = null;
        //controller = (ClientHandlerInterface) rmiRegistry.lookup("ClientHandler");
        } catch (RemoteException e) {
            e.printStackTrace();
        //} catch (NotBoundException e) {
         //   e.printStackTrace();
        }
        //new ObserverView(controller).run();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Stage secondaryStage = new Stage();
        secondaryStage.initStyle(StageStyle.UNDECORATED);
        scene.setFill(Color.TRANSPARENT);
        secondaryStage.setScene(scene);
        secondaryStage.initStyle(StageStyle.TRANSPARENT);
        secondaryStage.show();
        //rootPane.getScene().getWindow().hide(); //todo chiudere finestra selezione socket/rmi
    }
}
