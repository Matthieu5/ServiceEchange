package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SelectProfilMessageEntrantSQL {
    public ArrayList getSelectProfilMessageEntrantSQL(String id, String nom, String prenom) throws Exception {
        String id_destinataire = null;
        ArrayList messagesEntrant = new ArrayList<>();
        try {

            //étape 1: charger la classe de driver
            Class.forName("org.postgresql.Driver");

            //étape 2: créer l'objet de connexion
            Connection con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/bd_projetService","postgres","admin");

            //étape 3: créer l'objet statement
            String query = "SELECT id_personne FROM personne where nom_personne=? AND prenom_personne=?";
            PreparedStatement updateSales = con.prepareStatement(query);

            updateSales.setString(1, nom);
            updateSales.setString(2, prenom);

            //étape 4: exécuter la requête
            ResultSet res = updateSales.executeQuery();
            if(res.next()) {
                id_destinataire = res.getObject(1).toString();
            }


            //étape 3: créer l'objet statement
            String query2 = "SELECT libelle_message, timestamp_message FROM message where id_personne=? AND id_personne_1=?";
            PreparedStatement updateSales2 = con.prepareStatement(query2);
            updateSales2.setInt(1, Integer.parseInt(id_destinataire));
            updateSales2.setInt(2, Integer.parseInt(id));

            //étape 4: exécuter la requête
            ResultSet res2 = updateSales2.executeQuery();
            if(res2.next()) {
                messagesEntrant.add(res2.getObject(1).toString() + "=" + res2.getObject(2).toString());
            }

            //étape 5: fermez l'objet de connexion
            con.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return messagesEntrant;
    }
}
