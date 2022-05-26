package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import packet.AfficherProfilCategoriePacket;
import packet.LoginPacket;
import com.google.gson.Gson;
import java.net.NetworkInterface;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import static application.Main.socketClientGlobal;

public class ProfilsCategorieController implements Initializable {

    @FXML
    private Label TitreCategorie;
    @FXML
    private TableView <Person> ProfilListe;
    @FXML
    private TableColumn<Person, String> nomColumn;

    @FXML
    private TableColumn<Person, String> prenomColumn;

    @FXML
    private TableColumn<Person, String> descriptionColumn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        Main.afficherProfilCategorie("obtenirCategorie", getTitreCategorie());
        TitreCategorie.setText(getTitreCategorie());

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        try {
                            JSONArray js = ProfilCategorie.getProfils();

                            ObservableList<Person> persons = ProfilListe.getItems();
                            nomColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
                            prenomColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
                            descriptionColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("Description"));
                            for (int i=0; i < js.length(); i++) {
                                Person p = new Person();
                                for (int j=0; j < js.getJSONArray(i).length(); j++) {
                                    if(j==0) {
                                        p.setName(js.getJSONArray(i).get(j).toString());
                                    }else if (j==1) {
                                        p.setFirstName(js.getJSONArray(i).get(j).toString());
                                    }else if (j==2) {
                                        p.setDescription(js.getJSONArray(i).get(j).toString());
                                    }
                                }
                                persons.add(p);
                            }
                            ProfilListe.setItems(persons);

                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }
                },
                1000
        );
    }

    @FXML
    private void redirectionCategorie() {
        Main.showCategorieOverview();
    }
    @FXML
    private void redirectionMessage() {
        Main.showCategorieOverview();
    }

    private String getTitreCategorie() {
        CategorieController cg = new CategorieController();
        return cg.getProfilName();
    }

    public class Person {

        private String name;
        private String firstName;
        private String description;

        public Person() {
            this.name = "";
            this.firstName = "";
            this.description = "";
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }
        public String toString () {
            return this.getName() + this.getFirstName() + this.getDescription();
        }
    }
}
