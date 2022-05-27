package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import packet.AfficherProfilCategoriePacket;
import packet.LoginPacket;
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
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label note;

    protected static String nomFormat;
    protected static String prenomFormat;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        Main.afficherProfilCategorie("obtenirCategorie", getTitreCategorie());
        TitreCategorie.setText(getTitreCategorie());

        ProfilListe.setRowFactory( tv -> {
            TableRow<Person> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Person rowData = row.getItem();
                    nom.setText(rowData.name);
                    prenom.setText(rowData.firstName);
                    note.setText(rowData.description);
                }
            });
            return row ;
        });

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
                100
        );
    }


    @FXML
    private void redirectionCategorie() {
        Main.showCategorieOverview();
    }
    @FXML
    private void redirectionMessage() {

        nomFormat = String.valueOf(nom);
        nomFormat = nomFormat.substring(32, nomFormat.length() - 1);

        prenomFormat = String.valueOf(prenom);
        prenomFormat = prenomFormat.substring(35, prenomFormat.length() - 1);

        Main.showMessageOverview(nomFormat, prenomFormat);
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

    public static String getNomFormat() {
        return nomFormat;
    }

    public static String getPrenomFormat() {
        return prenomFormat;
    }
}
