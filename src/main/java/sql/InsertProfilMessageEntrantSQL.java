package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InsertProfilMessageEntrantSQL {
    public Boolean getInsertProfilMessageEntrantSQL(String id, String nom, String prenom, String message) throws Exception {
        String id_destinataire = null;
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
            String query2 = "INSERT libelle_message, id_personne, id_personne_1 FROM message;";
            PreparedStatement updateSales2 = con.prepareStatement(query2);
            updateSales2.setString(1, message);
            updateSales2.setInt(2, Integer.parseInt(id_destinataire));
            updateSales2.setInt(3, Integer.parseInt(id));

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
