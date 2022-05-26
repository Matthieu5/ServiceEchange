package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InsertProfilMessageSQL {
    public Boolean getInsertProfilMessageSQL(String id, String nom, String prenom, String categorie, String message) throws Exception {
        String id_destinataire = null;
        try {

            //étape 1: charger la classe de driver
            Class.forName("org.postgresql.Driver");

            //étape 2: créer l'objet de connexion
            Connection con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/bd_projetService","postgres","admin");

            //étape 3: créer l'objet statement
            String query = "SELECT id_personne FROM personne where nom_personne=? AND prenom_personne=? AND categorie_personne=?";
            PreparedStatement updateSales = con.prepareStatement(query);

            if(categorie.equals("Jardinage")) {
                categorie = "1";
            } else if(categorie.equals("Maçonnerie")) {
                categorie = "2";
            } else if(categorie.equals("Electricité")) {
                categorie = "3";
            } else if(categorie.equals("Peinture")) {
                categorie = "4";
            } else if(categorie.equals("Plomberie")) {
                categorie = "5";
            } else if(categorie.equals("Autre")) {
                categorie = "6";
            }

            updateSales.setString(1, nom);
            updateSales.setString(2, prenom);
            updateSales.setInt(3, Integer.parseInt(categorie));

            //étape 4: exécuter la requête
            ResultSet res = updateSales.executeQuery();
            if(res.next()) {
                id_destinataire = res.getObject(1).toString();
            }


            //étape 3: créer l'objet statement
            String query2 = "INSERT libelle_message, id_personne, id_personne_1 FROM message;";
            PreparedStatement updateSales2 = con.prepareStatement(query2);
            updateSales2.setString(1, message);
            updateSales2.setInt(2, Integer.parseInt(id));
            updateSales2.setInt(3, Integer.parseInt(id_destinataire));

            //étape 4: exécuter la requête
            updateSales2.executeUpdate();

            //étape 5: fermez l'objet de connexion
            con.close();
        }
        catch(Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
}
