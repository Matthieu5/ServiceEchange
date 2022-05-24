package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class CategorieController {

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
    protected void redirectionProfils(ActionEvent actionEvent) {
        Main.showProfilsOverview(((Button)actionEvent.getSource()).getText());
    }

    @FXML
    protected void redirectionParametre() {
        Main.showParametreOverview();
    }
}
