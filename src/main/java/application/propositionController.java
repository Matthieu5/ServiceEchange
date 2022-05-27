package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static application.ProfilsCategorieController.nomFormat;
import static application.ProfilsCategorieController.prenomFormat;

public class propositionController implements Initializable {
    @FXML
    private TextField nbHeure;
    @FXML
    private TextField descriptionPrestation;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void redirectionMessage() {
        Main.showMessageOverview(nomFormat, prenomFormat);
    }

    public void proposerPrestation() {
        Main.propositionPrestation("PropositionPrestation", nbHeure.getText(), descriptionPrestation.getText(), nomFormat, prenomFormat);
        redirectionMessage();
    }
}
