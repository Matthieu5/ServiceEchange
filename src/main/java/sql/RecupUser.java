package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class RecupUser {
    public ArrayList getUserMessage(String id) throws Exception {
        ArrayList users = new ArrayList<>();
        try {

            //étape 1: charger la classe de driver
            Class.forName("org.postgresql.Driver");

            //étape 2: créer l'objet de connexion
            Connection con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/bd_projetService","postgres","admin");

            //étape 3: créer l'objet statement
            String query = "SELECT id_personne_1 FROM message where id_personne=?";
            PreparedStatement updateSales = con.prepareStatement(query);
            updateSales.setInt(1, Integer.parseInt(id));

            //étape 4: exécuter la requête
            ResultSet res = updateSales.executeQuery();

            while(res.next()) {
                users.add(res.getString(1));
            }

            //étape 5: fermez l'objet de connexion
            con.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }

        return users;
    }
}
