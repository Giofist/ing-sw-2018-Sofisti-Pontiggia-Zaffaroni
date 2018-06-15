package it.polimi.ingsw.ClientViewGUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
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
import java.rmi.RemoteException;

public class LogInController {

    public Stage primaryStage;
    private int connection=0;

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

        Parent menu = null;
        try {
            menu = FXMLLoader.load(getClass().getResource("/Menu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(menu);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.setMaximized(true);
            appStage.show();
    }

    @FXML
    void setconnection(ActionEvent event) {  //TODO fra se isSelected True Socket altrimenti RMI
        if(ConnectionSetUp.isSelected()){
            connectionMessage.setText("Socket selected!");
        }
        else connectionMessage.setText("RMI selected!");
    }

    @FXML
    void signUpOperation(MouseEvent event) {

        signUpConfirmation.setText("Utente registrato!");
       /* try {
            serverController.register(username.getCharacters(), password.getCharacters());
            LogInError.setText("Hai creato un nuovo account!"
        } catch (RemoteException e) {
            LogInError.setText(e.getMessage());
            remoteException = true;
        }
        */
    }

    public void connect(ActionEvent actionEvent) {
        connectionMessage.setText("Connection up!");
    }
}
