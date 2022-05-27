package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import static application.Main.envoyerNote;

public class NotationController {
    @FXML
    private TextField notePrestation;

    public void ValiderNote(ActionEvent actionEvent) {
        envoyerNote(Main.idPresta, notePrestation.getText());
    }
}
