package application;

import javafx.fxml.FXML;

public class CategorieController {

    @FXML
    protected void deconnexion() {
        Main.deconnexionMain("Deconnexion");
        Main.showConnexionOverview();
    }

    @FXML
    protected void redirectionPrestation() {
        Main.showParametreOverview();
    }

    @FXML
    protected void redirectionProfils() {
        Main.showProfilsOverview();
    }

    @FXML
    protected void redirectionParametre() {
        Main.showParametreOverview();
    }
}
