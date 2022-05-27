package application;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONArray;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import static application.Main.accepterPrestation;


public class PrestationController implements Initializable {

    @FXML
    private Button profil;
    @FXML
    private Button retour;
    @FXML
    private Button deconnexion;
    @FXML
    private TableView<Prestation> tablePrestations;
    @FXML
    private TableColumn<Prestation, String> idPrestation;
    @FXML
    private TableColumn<Prestation, String> dateColumn;
    @FXML
    private TableColumn<Prestation, String> realiseColumn;
    @FXML
    private TableColumn<Prestation, String> accepteColumn;
    @FXML
    private TableColumn<Prestation, String> descriptionColumn;
    @FXML
    private TableColumn<Prestation, String> noteColumn;
    @FXML
    private TableColumn<Prestation, String> categorieColumn;
    @FXML
    private TableColumn<Prestation, String> nomColumn;
    @FXML
    private Button buttonAccepter;
    @FXML
    private Button btnValider;

    private int idSelectionnee;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tablePrestations.setRowFactory( tv -> {
            TableRow<Prestation> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Prestation presta = tablePrestations.getSelectionModel().getSelectedItem();
                    if(presta.getNom_personne().equals("Moi")) {
                        btnValider.setVisible(true);
                        buttonAccepter.setVisible(false);
                    }else {
                        btnValider.setVisible(false);
                        buttonAccepter.setVisible(true);
                        idSelectionnee= Integer.parseInt(presta.getId_prestation());
                    }
                }
            });
            return row ;
        });

        try {
            JSONArray js = tabPresta.getArray();
            ObservableList<Prestation> presta = tablePrestations.getItems();
            idPrestation.setCellValueFactory(new PropertyValueFactory<Prestation, String>("id_prestation"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<Prestation, String>("date_prestation"));
            realiseColumn.setCellValueFactory(new PropertyValueFactory<Prestation, String>("realise"));
            accepteColumn.setCellValueFactory(new PropertyValueFactory<Prestation, String>("accepte"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<Prestation, String>("description_prestation"));
            noteColumn.setCellValueFactory(new PropertyValueFactory<Prestation, String>("note_prestation"));
            categorieColumn.setCellValueFactory(new PropertyValueFactory<Prestation, String>("nom_categorie"));
            nomColumn.setCellValueFactory(new PropertyValueFactory<Prestation, String>("nom_personne"));

            for (int i=0; i < js.length(); i++) {
                Prestation p = new Prestation();
                for (int j=0; j < js.getJSONArray(i).length(); j++) {
                    if(j==0) {
                        p.setId_prestation(js.getJSONArray(i).get(j).toString());
                    }else if (j==1) {
                        p.setDate_prestation(js.getJSONArray(i).get(j).toString());
                    }else if (j==2) {
                        p.setRealise(js.getJSONArray(i).get(j).toString());
                    }else if (j==3) {
                        p.setAccepte(js.getJSONArray(i).get(j).toString());
                    }else if (j==4) {
                        p.setDescription_prestation(js.getJSONArray(i).get(j).toString());
                    } else if (j==5) {
                        p.setNote_prestation(js.getJSONArray(i).get(j).toString());
                    }else if (j==6) {
                        p.setNom_categorie(js.getJSONArray(i).get(j).toString());
                    }else if (j==7) {
                        p.setNom_personne(js.getJSONArray(i).get(j).toString());
                    }
                }
                System.out.println(js.get(i));
                presta.add(p);
            }
            tablePrestations.setItems(presta);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void redirectionRetour () {Main.showCategorieOverview();}
    public void redirectionDeconnexion () {Main.deconnexionMain("Deconnexion");Main.showConnexionOverview();}
    public void redirectionProfil () {Main.showParametreOverview();}

    public class Prestation {
        private String id_prestation;
        private String date_prestation;
        private String realise;
        private String accepte;
        private String description_prestation;
        private String note_prestation;
        private String nom_categorie;
        private String nom_personne;

        public String getId_prestation() {return id_prestation;}
        public String getDate_prestation() {return date_prestation;}
        public String getNote_prestation() {return note_prestation;}
        public String getDescription_prestation() {return description_prestation;}
        public String getNom_categorie() {return nom_categorie;}
        public String isRealise() {
            if (this.realise.equals("t")) {
                return "Oui";
            }else if (this.realise.equals("f")) {
                return "Non";
            }else {
                return "";
            }
        }
        public String getAccepte() {
            if (this.accepte.equals("t")) {
                return "Oui";
            }else if (this.realise.equals("f")) {
                return "Non";
            }else {
                return "";
            }
        }
        public String getNom_personne() {return nom_personne;}

        public void setId_prestation(String id_prestation) {this.id_prestation = id_prestation;}
        public void setDate_prestation(String date_prestation) {this.date_prestation = date_prestation;}
        public void setDescription_prestation(String description_prestation) {this.description_prestation = description_prestation;}
        public void setNom_categorie(String nom_categorie) {this.nom_categorie = nom_categorie;}
        public void setNote_prestation(String note_prestation) {this.note_prestation = note_prestation;}
        public void setRealise(String realise) {this.realise = realise;}
        public void setNom_personne(String nom_personne) {this.nom_personne = nom_personne;}
        public void setAccepte(String accepte) {this.accepte = accepte;}
    }

    public static class tabPresta {
        public static JSONArray array;
        public tabPresta (JSONArray array) {
            this.array=array;
        }
        public static JSONArray getArray() {
            return array;
        }
        public void setArray(JSONArray array) {
            this.array = array;
        }
    }

    public void ValiderPrestation(ActionEvent actionEvent) {
        Prestation presta = tablePrestations.getSelectionModel().getSelectedItem();
        Main.showNotationOverview(presta.getId_prestation());
    }

    public void AccepterPrestation(ActionEvent actionEvent) {
        accepterPrestation("accepter prestation",idSelectionnee);
    }
}
