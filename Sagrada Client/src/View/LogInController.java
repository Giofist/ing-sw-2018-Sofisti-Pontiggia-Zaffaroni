package View;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URI;

public class LogInController {

    @FXML
    private JFXTextField username;

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
    void closeClient(MouseEvent event) {
        System.exit(0);
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
        try {
            FXMLLoader.load(getClass().getResource("Menu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void signUpOperation(MouseEvent event) {

    }

}
