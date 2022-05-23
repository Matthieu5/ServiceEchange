package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SelectProfilSQL {
    public ArrayList getSQLProfil(String id) throws Exception {
        ArrayList infos = new ArrayList<>();
        try {

            //étape 1: charger la classe de driver
            Class.forName("org.postgresql.Driver");

            //étape 2: créer l'objet de connexion
            Connection con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/bd_projetService","postgres","admin");

            //étape 3: créer l'objet statement
            String query = "SELECT nom_personne, prenom_personne, tel_personne, mail_personne, adresse_personne, age_personne FROM personne where id_personne=?";
            PreparedStatement updateSales = con.prepareStatement(query);
            updateSales.setString(1, id);

            //étape 4: exécuter la requête
            ResultSet res = updateSales.executeQuery();
            if(res.next()) {
                infos.add("");
                infos.add("");
                infos.add("");
                infos.add("");
                infos.add("");
                infos.add("");
            }

            //étape 3: créer l'objet statement
            String query2 = "SELECT date_inscription, moyenne_utilisateur, description_utilisateur, compteur_utilisateur, actif FROM utilisateur where id_personne=?";
            PreparedStatement updateSales2 = con.prepareStatement(query2);
            updateSales2.setString(1, id);

            //étape 4: exécuter la requête
            ResultSet res2 = updateSales.executeQuery();
            if(res.next()) {
                infos.add("");
                infos.add("");
                infos.add("");
                infos.add("");
                infos.add("");
            }

            //étape 5: fermez l'objet de connexion
            con.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return infos;
    }
}
