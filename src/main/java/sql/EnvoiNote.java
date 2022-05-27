package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EnvoiNote {
    public void insertNote(String id, String note) throws Exception {
        try {

            //étape 1: charger la classe de driver
            Class.forName("org.postgresql.Driver");

            //étape 2: créer l'objet de connexion
            Connection con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/bd_projetService","postgres","admin");

            //étape 3: créer l'objet statement
            String query =
                    "UPDATE prestation SET note_prestation=? WHERE id=?";

            PreparedStatement updateSales = con.prepareStatement(query);
            updateSales.setDouble(1, Double.parseDouble(note));
            updateSales.setInt(1, Integer.parseInt(id));

            //étape 4: exécuter la requête
            updateSales.executeUpdate();

            //étape 5: fermez l'objet de connexion
            con.close();

        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}
