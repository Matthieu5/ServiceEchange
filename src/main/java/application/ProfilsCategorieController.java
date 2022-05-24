package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfilsCategorieController implements Initializable {

    @FXML
    private Label TitreCategorie;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TitreCategorie.setText(getTitreCategorie());
    }

    public String getTitreCategorie() {
        CategorieController cg = new CategorieController();
        return cg.getProfilName();
    }

}
