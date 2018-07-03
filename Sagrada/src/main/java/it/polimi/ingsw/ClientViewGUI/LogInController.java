package it.polimi.ingsw.ClientViewGUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import it.polimi.ingsw.NetworkClient.SocketClientListener;
import it.polimi.ingsw.NetworkClient.SocketController;
import it.polimi.ingsw.ServerController.ClientHandlerInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import com.jfoenix.controls.JFXToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.net.URI;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

public class LogInController extends AbstractController implements Initializable{
    private String ipAddr = "127.0.0.1";
    private int port = 1337;
    public Stage primaryStage;

    public LogInController(){
       ObserverGUI.Singleton().setController(this);
    }

    @FXML
    private JFXTextField username;

    @FXML
    private JFXTextField IPAddress;

    @FXML
    private JFXTextField PortField;

    @FXML
    private JFXToggleButton ConnectionSetUp;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton ConnectButton;

    @FXML
    private Text LogInError;

    @FXML
    private Text connectionMessage;

    @FXML
    private Text connectionError;

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
        Parent menu = null;
        try {
           ObserverGUI.Singleton().getServerController().login(username.getCharacters().toString(), password.getCharacters().toString(), ObserverGUI.Singleton());
            ObserverGUI.Singleton().setUsername(username.getCharacters().toString());
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
        } catch (RemoteException e) {
            LogInError.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
        }
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
           ObserverGUI.Singleton().getServerController().register(username.getCharacters().toString(), password.getCharacters().toString());
            signUpConfirmation.setText("Hai creato un nuovo account!");
        } catch (RemoteException e) {
            LogInError.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
        }

    }

    public void connect(ActionEvent actionEvent) {
        ipAddr = IPAddress.getCharacters().toString();
        port = Integer.parseInt(PortField.getCharacters().toString());

        boolean correct = true;
        if (ConnectionSetUp.isSelected()) {
            try {
                Socket socket = new Socket(ipAddr, port);
                SocketClientListener listener = new SocketClientListener(socket);
                SocketController socketController = new SocketController(listener);
                ObserverGUI.Singleton().setServerController(socketController);
                listener.setController(socketController, ObserverGUI.Singleton());
                new Thread(listener).start();
                connectionMessage.setText("Connection up!");
                ConnectButton.setVisible(false);
            } catch (IOException e) {
                correct = false;
                connectionError.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
            }
        } else {
            Registry rmiRegistry;
            try {
                rmiRegistry = LocateRegistry.getRegistry(ipAddr);
                ClientHandlerInterface controller = (ClientHandlerInterface) rmiRegistry.lookup("ClientHandler");
                ObserverGUI.Singleton().setServerController(controller);
                connectionMessage.setText("Connection up!");
                ConnectButton.setVisible(false);
            } catch (Exception e) {
                correct = false;
                connectionMessage.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PortField.setText(String.valueOf(port));
        IPAddress.setText(String.valueOf(ipAddr));
    }


    }

