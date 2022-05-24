package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static application.Profil.*;

public class ProfilController implements Initializable {
    @FXML
    private Label dateInscriptionProfil;
    @FXML
    private Label noteProfil;
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
        Main.modifierProfil("ModifierProfil", nomProfil.getText(), prenomProfil.getText(), telProfil.getText(), ageProfil.getText(), mailProfil.getText(), adresseProfil.getText(), "test", actifProfil.getText());
    }

    public void redirectionCategorie(ActionEvent actionEvent) {
        Main.showCategorieOverview();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void entrerDonnees() {
        nomProfil.setText(getNom());
        prenomProfil.setText(getPrenom());
        telProfil.setText(getTel());
        mailProfil.setText(getMail());
        adresseProfil.setText(getAdresse());
        ageProfil.setText(getAge());
        dateInscriptionProfil.setText(getDateInscription());
        noteProfil.setText(getNote());
        actifProfil.setText(getActif());
        if(getActif().equals(true)) {
            actifProfil.setText("Actif");
        } else {
            actifProfil.setText("Pas actif");
        }
        //descriptionProfil.setText(getDescription());
    }
}
