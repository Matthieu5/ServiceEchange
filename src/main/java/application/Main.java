package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

import packet.*;
import xyz.baddeveloper.lwsl.client.SocketClient;
import xyz.baddeveloper.lwsl.client.exceptions.ConnectException;
import org.json.JSONObject;

public class Main extends Application {

    private Stage stage;
    private static BorderPane rootLayout;
    public static SocketClient socketClientGlobal;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        this.stage.setTitle("Service Exchanges");

        SocketClient socketclient = new SocketClient("192.168.1.23", 25566)
                .addConnectEvent(onConnect -> System.out.println("Connected!"))
                .addDisconnectEvent(onDisconnect -> System.out.println("Disconnected!"))
                .addPacketReceivedEvent(((socket, packet) -> {
                    try {
                        lireMessageRetour(packet.getObject());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }))
                .addPacketSentEvent(((socketClient, packet) -> System.out.println(String.format("Sent packet %s to %s.", packet.getObject().toString(), socketClient.getAddress()))));
        try {
            socketclient.connect();
        } catch (ConnectException e) {
            e.printStackTrace();
        }
        socketClientGlobal = socketclient;
        initRootLayout();
        showConnexionOverview();
    }

    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout, 900, 600);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void lireMessageRetour(JSONObject message) throws Exception {
        System.out.println(message);
        if(message.getString("typePacket").equals("Login retour")) {
            if (message.getString("message").equals("true")) {
                MainController.retourConnexion(true);
            } else {
                MainController.retourConnexion(false);
            }
        } else if(message.getString("typePacket").equals("Inscription retour")) {
            if (message.getString("message").equals("true")) {
                InscriptionController.retourInscription(true);
            } else {
                InscriptionController.retourInscription(false);
            }
        } else if(message.getString("typePacket").equals("Demande Profil retour")) {
            String nom = message.getString("nom");
            String prenom = message.getString("prenom");
            String tel = message.getString("tel");
            String mail = message.getString("mail");
            String adresse = message.getString("adresse");
            String age = message.getString("age");
            String dateInscription = message.getString("dateInscription");
            String moyenne = message.getString("moyenne");
            String description = message.getString("description");
            String categorie = message.getString("categorie");
            String compteur = message.getString("compteur");
            String actif = message.getString("actif");

            Profil p = new Profil(nom, prenom, age, tel, mail, adresse, actif, categorie, dateInscription, moyenne, compteur, description);

            Platform.runLater(() -> {
                try {
                    // Load connexion overview.
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(Main.class.getResource("/view/parametreOverview.fxml"));
                    AnchorPane parametreOverview = (AnchorPane) loader.load();

                    // Set connexion overview into the center of root layout.
                    rootLayout.setCenter(parametreOverview);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } else if(message.getString("typePacket").equals("Update Profil retour")) {
            System.out.println(message.getString("message"));

        } else if(message.getString("typePacket").equals("Profil Categorie retour")) {

        }
    }


    public static void showConnexionOverview() {
        Platform.runLater(() -> {
            try {
                // Load connexion overview.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("/view/connexionOverview.fxml"));
                AnchorPane connexionOverview = (AnchorPane) loader.load();

                // Set connexion overview into the center of root layout.
                rootLayout.setCenter(connexionOverview);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void showInscriptionOverview() {
        Platform.runLater(() -> {
            try {
                // Load person overview.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("/view/inscriptionOverview.fxml"));
                AnchorPane inscriptionOverview = (AnchorPane) loader.load();

                // Set person overview into the center of root layout.
                rootLayout.setCenter(inscriptionOverview);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void showCategorieOverview() {
        Platform.runLater(() -> {
            try {
                // Load connexion overview.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("/view/categorieOverview.fxml"));
                AnchorPane categorieOverview = (AnchorPane) loader.load();

                // Set connexion overview into the center of root layout.
                rootLayout.setCenter(categorieOverview);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void showProfilsOverview() {
        Platform.runLater(() -> {
            try {
                // Load connexion overview.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("/view/profilsOverview.fxml"));
                AnchorPane profilsOverview = (AnchorPane) loader.load();

                // Set connexion overview into the center of root layout.
                rootLayout.setCenter(profilsOverview);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void showParametreOverview() {
        demandeProfil("DemandeProfil");
    }

    public static void afficherProfilCategorie(String typePacket,String categorie) {
        socketClientGlobal.sendPacket(new AfficherProfilCategoriePacket(typePacket,categorie));
    }

    public static void connexionMain(String typePacket, String id, String mdp) {
        socketClientGlobal.sendPacket(new LoginPacket(typePacket, id, mdp));
    }

    public static void inscriptionMain(String typePacket, String nom, String prenom, String tel, String mail, String adresse, int age, String mdp, String login) {
        socketClientGlobal.sendPacket(new InscriptionPacket(typePacket,nom,prenom,tel,mail,adresse,age,mdp,login));
    }

    public static void demandeProfil(String typePacket) {
        socketClientGlobal.sendPacket(new DemandeProfil(typePacket));
    }

    public static void modifierProfil(String typePacket, String nom, String prenom, String tel, String age, String mail, String adresse, String categorie, String actif, String description) {
        socketClientGlobal.sendPacket(new ModifProfilPacket(typePacket,nom,prenom,tel,age,mail,adresse,categorie,actif,description));
    }

    public static void deconnexionMain(String typePacket) {
        socketClientGlobal.sendPacket(new DeconnexionPacket(typePacket));
    }

    public static void showPrestationOverview() {
        Platform.runLater(() -> {
            try {
                // Load connexion overview.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("/view/prestationOverview.fxml"));
                AnchorPane prestationOverview = (AnchorPane) loader.load();

                // Set connexion overview into the center of root layout.
                rootLayout.setCenter(prestationOverview);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}