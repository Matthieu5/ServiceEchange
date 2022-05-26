package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.json.JSONArray;

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
            Main.envoyerMessage("EnvoiMessage", ProfilsCategorieController.getNomFormat(), ProfilsCategorieController.getPrenomFormat(), messageBox.getText());
            messageBox.clear();
        }
    }

    public static void afficherMessage(JSONArray messagesSortant, JSONArray messagesEntrant) {
        for(int i = 0; i < messagesSortant.length(); i++) {
            System.out.println(messagesSortant.get(i));
        }
    }

    public void redirectionProposition(ActionEvent actionEvent) {
        Main.showPropositionOverview();
    }
}