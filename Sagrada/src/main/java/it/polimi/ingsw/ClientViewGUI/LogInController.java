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
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

public class LogInController implements Initializable{
    private String ipAddr = "127.0.0.1";
    private int port = 1337;
    public Stage primaryStage;
    private Boolean connected = false;

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
       /*try {
            EntryPoint.Singleton().getServerController().login(username.getCharacters().toString(), password.getCharacters().toString());  //TODO da un sacco di errori in RMI
            LogInError.setText("Hai creato un nuovo account!");
        } catch (RemoteException e) {
            LogInError.setText(e.getMessage());
        }*/
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
       /*try {
           EntryPoint.Singleton().getServerController().register(username.getCharacters().toString(), password.getCharacters().toString());  //TODO da un sacco di errori in RMI
            LogInError.setText("Hai creato un nuovo account!");
        } catch (RemoteException e) {
            LogInError.setText("Esiste già un utente con lo stesso nome!");
        }*/

    }

    public void connect(ActionEvent actionEvent) {
       /* ipAddr = String.valueOf(IPAddress);
        //SocketClientListener.setPort(Integer.parseInt(String.valueOf(Port))); //todo come lo sistemo???
        Boolean correct = true;
        if(connected == true){
            if(ConnectionSetUp.isSelected()){
                SocketClientListener listener;
                try {
                    listener = new SocketClientListener(ipAddr);
                }catch (IOException e){
                    correct=false;
                    connectionError.setText("IP errato!");
                }
                EntryPoint.Singleton().setSocketController(new SocketController( listener));
                listener.setController(EntryPoint.Singleton().getSocketController(), EntryPoint.Singleton());
                new Thread(listener).start();
            }
            else {
                Registry rmiRegistry = null;
                try {
                    rmiRegistry = LocateRegistry.getRegistry(ipAddr);
                } catch (RemoteException e) {
                    correct=false;
                    connectionMessage.setText("IP errato!");
                }
                ClientHandlerInterface controller = null;
                try {
                    controller = (ClientHandlerInterface) rmiRegistry.lookup("ClientHandler");
                } catch (RemoteException e) {
                    correct = false;
                    connectionError.setText("RMI error!");
                } catch (NotBoundException e) {
                    correct = false;
                    connectionError.setText("RMI error!");
                }
                EntryPoint.Singleton().setServerController(controller);

            }
            if(correct) {
                ConnectButton.setText("Connect");
                connectionMessage.setText("Disconnected!");
                connected = false;
            }
            else{
                connectionError.setText("Qualcosa è andato storto!");
                }
        }
        else{
            ConnectButton.setText("Disconnect");
            connectionMessage.setText("Connection up!");
            connected = true;
        }
        */
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PortField.setText(String.valueOf(port));
        IPAddress.setText(String.valueOf(ipAddr));
    }
}
