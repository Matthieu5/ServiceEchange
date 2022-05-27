package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class messageAvanceeController implements Initializable {
    @FXML
    private ListView userList;

    public void redirectionCategorie(ActionEvent actionEvent) {
        Main.showCategorieOverview();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userList.setItems(User.getUsers());
    }
}
