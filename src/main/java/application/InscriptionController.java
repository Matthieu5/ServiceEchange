package application;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

                if (mdp.getText().equals(confirmMdp.getText())) {
                    Main.inscriptionMain("Inscription", nom.getText(), prenom.getText(), telephone.getText(), email.getText(), adresse.getText(), Integer.parseInt(age.getText()), mdp.getText(), identifiant.getText());
                }else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Mot de passe non valide");
                    alert.setContentText("La confirmation du mot de passe n'est pas valide");
                    alert.showAndWait();
                    System.out.println("La confirmation de mdp n'est pas valide");
                }

            }catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Champs renseignés non valide");
                alert.setContentText("Un ou plusieurs des caractères ne sont pas valides");
                alert.showAndWait();
                System.out.println("Un ou plusieurs des caractères ne sont pas valides");
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
