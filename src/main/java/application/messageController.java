package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.json.JSONArray;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class messageController implements Initializable {
    @FXML private TextArea messageBox;
    @FXML private ListView chatPane;

    @FXML
    protected void redirectionProfil(ActionEvent actionEvent) {
        Main.showProfilsOverview();
    }

    public void sendMethod(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            sendButtonAction();
        }
    }

    public void sendButtonAction() throws IOException {
        String msg = messageBox.getText();
        if (!messageBox.getText().isEmpty()) {
            //Listener.send(msg);
            Main.envoyerMessage("EnvoiMessage", ProfilsCategorieController.getNomFormat(), ProfilsCategorieController.getPrenomFormat(), messageBox.getText());
            messageBox.clear();
        }
    }

    public void redirectionProposition(ActionEvent actionEvent) {
        Main.showPropositionOverview();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> noMessage = FXCollections.observableArrayList();
        noMessage.add("Pas de messages");
        if(Main.items.equals(null) || Main.items.equals("")) {
            chatPane.setItems(noMessage);
        } else {
            chatPane.setItems(Main.items);
        }
    }
}