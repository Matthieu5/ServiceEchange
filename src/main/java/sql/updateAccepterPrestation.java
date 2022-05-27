package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class updateAccepterPrestation {
    public Boolean updateAccepterPrestation(int id) throws Exception {

        try {

            //étape 1: charger la classe de driver
            Class.forName("org.postgresql.Driver");

            //étape 2: créer l'objet de connexion
            Connection con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/bd_projetService","postgres","admin");

            //étape 3: créer l'objet statement
            String query = "UPDATE prestation set accepte=true where numero_prestation=?";
            PreparedStatement updateSales = con.prepareStatement(query);
            updateSales.setInt(1, id);
            updateSales.executeUpdate();

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
