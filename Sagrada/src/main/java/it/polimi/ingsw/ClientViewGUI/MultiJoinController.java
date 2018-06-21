package it.polimi.ingsw.ClientViewGUI;

import com.google.inject.internal.util.Strings;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

public class MultiJoinController implements Initializable{

    @FXML
    private AnchorPane joinPane;

    @FXML
    private Text ErrorMessage;

    @FXML
    private JFXListView<String> gameList;

    @FXML
    private JFXButton joinaGame;

    @FXML
    private JFXButton Back;

   @Override
   public void initialize(URL url, ResourceBundle rb){
       ObservableList<String> data = FXCollections.observableArrayList("gio", "gioco", "ehy","gio", "gioco", "ehy","gio", "gioco", "ehy","gio", "gioco", "ehy", "gio", "gioco", "ehy","gio", "gioco", "ehy");
       gameList.setItems(data);
   }

    public void SelectGame(MouseEvent mouseEvent) {
        ErrorMessage.setText("clicked");
    }

    public void JoinGame(ActionEvent actionEvent) {
       /*ObservableList<String> selected = null;
        ErrorMessage.setText(selected.toString()); not working selection*/
    }

    public void goBack(ActionEvent actionEvent) {
        try {
            joinPane.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/MultiSelectMode.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
