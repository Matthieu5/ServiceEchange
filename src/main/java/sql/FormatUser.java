package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FormatUser {
    public ArrayList getUsersFormat(ArrayList users) throws Exception {
        ArrayList usersFormat = new ArrayList<>();
        try {

            //étape 1: charger la classe de driver
            Class.forName("org.postgresql.Driver");

            //étape 2: créer l'objet de connexion
            Connection con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/bd_projetService","postgres","admin");

            //étape 3: créer l'objet statement
            for(int i = 0; i < users.size(); i++) {
                String query = "SELECT nom_personne, prenom_personne FROM personne where id_personne=?";
                PreparedStatement updateSales = con.prepareStatement(query);
                updateSales.setInt(1, Integer.parseInt(String.valueOf(users.get(i))));

                //étape 4: exécuter la requête
                ResultSet res = updateSales.executeQuery();

                while (res.next()) {
                    usersFormat.add(res.getString(1) + " " + res.getString(2));
                }

                //étape 5: fermez l'objet de connexion
                con.close();
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }

        return usersFormat;
    }
}
