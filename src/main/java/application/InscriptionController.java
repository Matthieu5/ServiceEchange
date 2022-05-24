package application;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import static java.lang.String.valueOf;

public class InscriptionController {

    @FXML
    private TextField identifiant;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private PasswordField mdp;
    @FXML
    private PasswordField confirmMdp;
    @FXML
    private TextField email;
    @FXML
    private TextField telephone;
    @FXML
    private TextField adresse;
    @FXML
    private TextField age;

    // bouton valider inscription
    @FXML
    protected void valider() throws NumberFormatException {


            try {

                Main.inscriptionMain("Inscription", nom.getText(), prenom.getText(), telephone.getText(), email.getText(), adresse.getText(), Integer.parseInt(age.getText()), mdp.getText(), identifiant.getText());

            }catch (NumberFormatException e) {
                e.printStackTrace();
            }



    }

    @FXML
    protected static void retourInscription(boolean reponse) throws Exception {
        if(reponse == true) {
            redirectionCategorie();
        } else {
            System.out.println("Problème lors de l'inscription");
        }
    }

    @FXML
    protected static void redirectionCategorie() {
        Main.showCategorieOverview();
    }

    @FXML
    protected void redirectionConnexion() {
        Main.showConnexionOverview();
    }
}
