package it.polimi.ingsw.ClientViewGUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    private JFXButton SignUpButton;

    @FXML
    private ImageView SagradaIcon;

    @FXML
    private Label Close;

    @FXML
    private Text LogInError;

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
        System.out.println("pressed");
        try {
            java.awt.Desktop.getDesktop().browse(URI.create("http://www.craniocreations.it/prodotto/sagrada/"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void signInOperation(MouseEvent event) {
        /*try {
            serverController.logIn(username.getCharacters(), password.getCharacters());
        } catch (RemoteException e) {
            LogInError.setText(e.getMessage());
            remoteException = true;
        }

        try {
            FXMLLoader.load(getClass().getResource("/Menu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        primaryStage.close();
        Stage primaryStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/Menu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Menu");
        primaryStage.show();

        /* primaryStage.close();
        FXMLLoader loader = new FXMLLoader();
        Stage menuStage = new Stage();
        Parent root = null;
        loader.setLocation(getClass().getResource("/Menu.fxml"));
        try {
           root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ((MenuController) loader.getController()).setmenuStage(menuStage);
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Menu");
        primaryStage.show();
*/

    }
    @FXML
    void setconnection(ActionEvent event) {  //TODO fra se isSelected True Socket altrimenti RMI
        if(ConnectionSetUp.isSelected()){
            LogInError.setText("Socket");
        }
        else LogInError.setText("RMI");
    }

    @FXML
    void signUpOperation(MouseEvent event) {
       /* try {
            serverController.register(username.getCharacters(), password.getCharacters());
            LogInError.setText("Hai creato un nuovo account!"
        } catch (RemoteException e) {
            LogInError.setText(e.getMessage());
            remoteException = true;
        }
        */
    }

}
