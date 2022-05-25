package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfilsCategorieController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TitreCategorie.setText(getTitreCategorie());

        try {
            Main.afficherProfilCategorie("obtenirCategorie",getTitreCategorie() );
        }catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Label TitreCategorie;

    @FXML
    private void redirectionCategorie() {Main.showCategorieOverview();}

    private String getTitreCategorie() {
        CategorieController cg = new CategorieController();
        return cg.getProfilName();
    }

    @FXML
    private void redirectionMessage() {Main.showMessageOverview();}

}
