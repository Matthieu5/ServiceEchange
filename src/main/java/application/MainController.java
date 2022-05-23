package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MainController {
    @FXML
    private Hyperlink lienInscription;

    @FXML
    private TextField identifiant;

    @FXML
    private PasswordField mdp;

    @FXML
    protected void redirectionInscription() {
        Main.showInscriptionOverview();
    }

    // bouton connexion
    @FXML
    protected void connexion() throws Exception {
        Main.connexionMain("Login", identifiant.getText(), mdp.getText());
    }

    @FXML
    protected static void retourConnexion(boolean reponse) throws Exception {
        if(reponse == true) {
            redirectionCategorie();
        } else {
            System.out.println("Mauvais identifiants");
        }
    }

    @FXML
    public static void redirectionCategorie() {
        Main.showCategorieOverview();
    }

    @FXML
    protected void redirectionProfils() {
        Main.showProfilsOverview();
    }

    @FXML
    protected void redirectionPrestation() {
        Main.showParametreOverview();
    }

    @FXML
    protected void redirectionParametre() {
        Main.showParametreOverview();
    }

    @FXML
    protected void ModifierProfil() {

    }

    @FXML
    protected void deconnexion() {
        Main.showConnexionOverview();
        Main.deconnexionMain("Deconnexion");
    }

    public void redirectionCategorie(ActionEvent actionEvent) {
        Main.showCategorieOverview();
    }
}