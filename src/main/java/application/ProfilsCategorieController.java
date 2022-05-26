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
    private TableView <Customer> ProfilListe;
    @FXML
    private TableColumn<Customer, String> nomColumn;

    @FXML
    private TableColumn<Customer, Integer> prenomColumn;

    @FXML
    private TableColumn<Customer, Integer> descriptionColumn;


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
                            JSONObject test;
                            for (int i=0; i < js.length(); i++) {
                                System.out.println(js.get(i));
                                System.out.println(new Gson().toJson(mobilePhone));)

                            }


                            nomColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
                            prenomColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("age"));
                            descriptionColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("number"));

                            Customer customer = new Customer("alexis",
                                    Integer.parseInt("16"),
                                    Integer.parseInt("16"));


                            ObservableList<Customer> customers = ProfilListe.getItems();
                            customers.add(customer);
                            ProfilListe.setItems(customers);



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

    private String getTitreCategorie() {
        CategorieController cg = new CategorieController();
        return cg.getProfilName();
    }

    public class Customer {

        private String name;
        private int age;
        private int number;

        public Customer(String name, int age, int number) {
            this.name = name;
            this.age = age;
            this.number = number;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }
}
