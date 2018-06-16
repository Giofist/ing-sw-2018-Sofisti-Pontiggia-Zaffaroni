package it.polimi.ingsw.ClientViewGUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import it.polimi.ingsw.ClientView.ObserverView;
import it.polimi.ingsw.NetworkClient.SocketClientListener;
import it.polimi.ingsw.NetworkClient.SocketController;
import it.polimi.ingsw.ServerController.ClientHandlerInterface;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class LogInController {

    private ClientHandlerInterface serverController;
    String ipAddr = "127.0.0.1";
    public Stage primaryStage;
    private int connection=0;
    private Boolean connected = false;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXToggleButton ConnectionSetUp;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton SigInButton;

    @FXML
    private JFXButton ConnectButton;

    @FXML
    private JFXButton SignUpButton;

    @FXML
    private ImageView SagradaIcon;

    @FXML
    private Label Close;

    @FXML
    private Text LogInError;

    @FXML
    private Text connectionMessage;
    @FXML
    private Text signUpConfirmation;

    @FXML
    void closeClient(MouseEvent event) {
        System.exit(0);
    }


    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    //go to the official sagrada store
    @FXML
    void goToWebSite(MouseEvent event) {
        try {
            java.awt.Desktop.getDesktop().browse(URI.create("http://www.craniocreations.it/prodotto/sagrada/"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void signInOperation(MouseEvent event) {
        /*
        try {
            serverController.login(username.getCharacters().toString(), password.getCharacters().toString(),);
        } catch (RemoteException e) {
            LogInError.setText(e.getMessage());
        }
*/ //mi manca l'observer e non so come crearlo
        Parent menu = null;
        try {
            menu = FXMLLoader.load(getClass().getResource("/Menu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(menu);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.setFullScreen(true);
            appStage.show();
    }

    @FXML
    void setconnection(ActionEvent event) {
        if(ConnectionSetUp.isSelected()){
            connectionMessage.setText("Socket selected!");
        }
        else connectionMessage.setText("RMI selected!");
    }

    @FXML
    void signUpOperation(MouseEvent event) {
       try {
            serverController.register(username.getCharacters().toString(), password.getCharacters().toString());
            LogInError.setText("Hai creato un nuovo account!");
        } catch (RemoteException e) {
            LogInError.setText(e.getMessage());
        }

    }

    public void connect(ActionEvent actionEvent) {

        if(connected == true){
            ConnectButton.setText("Connect");
            connectionMessage.setText("Disconnected!");
            connected = false;
        }
        else{
            if(ConnectionSetUp.isSelected()){
                ObserverView view = null;
                try {
                    view = new ObserverView();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    connectionMessage.setText("Connectin error!");
                    connected = false;
                }
                SocketClientListener listener = null;
                try {
                    listener = new SocketClientListener(ipAddr);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                SocketController socketController = new SocketController( listener);
                listener.setController(socketController, view);
                view.setServerController(socketController);
            }
            else {

                Registry rmiRegistry = null;
                try {
                    rmiRegistry = LocateRegistry.getRegistry(ipAddr);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                ClientHandlerInterface controller = null;
                try {
                    controller = (ClientHandlerInterface) rmiRegistry.lookup("ClientHandler");
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (NotBoundException e) {
                    e.printStackTrace();
                }

            }
            //ConnectButton.setStyle("fx-background-color: red"); //sparisce il bottone
            ConnectButton.setText("Disconnect");
            connectionMessage.setText("Connection up!");
            connected = true;
        }
    }
}
