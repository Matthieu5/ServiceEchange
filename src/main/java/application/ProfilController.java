package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static application.Profil.*;
import static application.Profil.getCategorie;

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
    private TextField categorieProfil;
    @FXML
    private TextField description;

    @FXML
    protected void ModifierProfil() {
        Main.modifierProfil("ModifierProfil", nomProfil.getText(), prenomProfil.getText(), telProfil.getText(), ageProfil.getText(), mailProfil.getText(), adresseProfil.getText(), categorieProfil.getText(), actifProfil.getText(), description.getText());
    }

    public void redirectionCategorie(ActionEvent actionEvent) {
        Main.showCategorieOverview();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomProfil.setText(getNom());
        prenomProfil.setText(getPrenom());
        telProfil.setText(getTel());
        mailProfil.setText(getMail());
        adresseProfil.setText(getAdresse());
        ageProfil.setText(getAge());
        dateInscriptionProfil.setText(getDateInscription());
        noteProfil.setText(getNote());
        System.out.println(getActif());
        if(getActif().equals("true")) {
            actifProfil.setText("Actif");
        } else {
            actifProfil.setText("Pas actif");
        }
        System.out.println(getCategorie());
        categorieProfil.setText(getCategorie());
        description.setText(getDescription());
    }
}
