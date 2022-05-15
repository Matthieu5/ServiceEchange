package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class ConnectUserSQL {
    public boolean getSQLServerConnection(String id, String mdp) throws Exception {
        boolean connection = true;
        try {

            //étape 1: charger la classe de driver
            Class.forName("org.postgresql.Driver");

            //étape 2: créer l'objet de connexion
            Connection con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/bd_projetService","postgres","admin");

            //étape 3: créer l'objet statement
            String query = "SELECT id_personne FROM personne where mdp_personne=? AND login_personne=?";
            PreparedStatement updateSales = con.prepareStatement(query);
            updateSales.setString(1, mdp);
            updateSales.setString(2, id);

            //étape 4: exécuter la requête
            ResultSet res = updateSales.executeQuery();
            if(!res.next()) {
                connection=false;
            }
            //étape 5: fermez l'objet de connexion
            con.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return connection;
    }
}