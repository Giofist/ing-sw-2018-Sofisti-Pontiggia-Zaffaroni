package it.polimi.ingsw.ClientViewGUI;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ConnectionController {

    @FXML
    private JFXButton RMIButton;

    @FXML
    private JFXButton SocketButton;

    @FXML
    void SocketClientLounch(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void rmiClientLounch(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
