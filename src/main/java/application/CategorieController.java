package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import static application.Profil.nom;
import static application.Profil.prenom;
import static application.ProfilsCategorieController.nomFormat;
import static application.ProfilsCategorieController.prenomFormat;


public class CategorieController  {

    @FXML
    private Button jardinage;
    @FXML
    private Button maconnerie;
    @FXML
    private Button electricite;
    @FXML
    private Button peinture;
    @FXML
    private Button plomberie;
    @FXML
    private Button autre;

    public static String profilName;


    @FXML
    protected  void redirectionDeconnexion () {

        Main.deconnexionMain("Deconnexion");
        Main.showConnexionOverview();

    }


    protected void redirectionConnexion() {
        Main.showParametreOverview();
    }

    @FXML
    protected void redirectionPrestation() {
        Main.showPrestationOverview();
    }

    @FXML
    protected void redirectionProfils(ActionEvent actionEvent) {
        setProfilName(((Button)actionEvent.getSource()).getText());
        Main.showProfilsOverview();
    }

    @FXML
    protected void redirectionMessageAvancee() {
        Main.showMessageAvanceeOverview();
    }

    @FXML
    protected void redirectionParametre() {
        Main.showParametreOverview();
    }

    public String getProfilName() {
        return this.profilName;
    }

    public void setProfilName (String s) {
        this.profilName=s;
    }
}
