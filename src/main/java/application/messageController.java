package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class messageController {
    @FXML private TextArea messageBox;

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
            Main.envoyerMessage("EnvoiMessage", messageBox.getText());
            messageBox.clear();
        }
    }
}