package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfilController implements Initializable {
    @FXML
    private Label dateInscriptionProfil;
    @FXML
    private TextField nomProfil;
    @FXML
    private TextField prenomProfil;
    @FXML
    private TextField ageProfil;
    @FXML
    private TextField telProfil;
    @FXML
    private TextField mailProfil;
    @FXML
    private TextField adresseProfil;
    @FXML
    private TextField actifProfil;
    @FXML
    private TextField descriptionProfil;

    @FXML
    protected void ModifierProfil() {
        Main.modifierProfil("ModifierProfil", nomProfil, prenomProfil, ageProfil, telProfil, mailProfil, adresseProfil, actifProfil, descriptionProfil);
    }

    public void redirectionCategorie(ActionEvent actionEvent) {
        Main.showCategorieOverview();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateInscriptionProfil.setText("test");
        nomProfil.setText("test");
    }
}
