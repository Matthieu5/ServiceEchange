package application;

import javafx.fxml.FXML;

public class CategorieController {

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
