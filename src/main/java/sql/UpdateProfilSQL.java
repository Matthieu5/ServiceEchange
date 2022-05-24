package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UpdateProfilSQL  {

    public Boolean updateProfilSQL(String id, String nom, String prenom, String tel, String age, String mail, String adresse, String description, String actif) throws Exception {
        try {

            //étape 1: charger la classe de driver
            Class.forName("org.postgresql.Driver");

            //étape 2: créer l'objet de connexion
            Connection con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/bd_projetService","postgres","admin");

            //étape 3: créer l'objet statement
            String query = "UPDATE personne set nom_personne=?, prenom_personne=?, tel_personne=?, mail_personne=?, adresse_personne=?, age_personne=? where id_personne=?";
            PreparedStatement updateSales = con.prepareStatement(query);
            updateSales.setString(1, nom);
            updateSales.setString(2, prenom);
            updateSales.setString(3, tel);
            updateSales.setString(4, mail);
            updateSales.setString(5, adresse);
            updateSales.setInt(6, Integer.parseInt(age));
            updateSales.setInt(7, Integer.parseInt(id));

            ResultSet res = updateSales.executeQuery();

            //étape 3: créer l'objet statement
            String query2 = "UPDATE utilisateur set description_utilisateur=?, actif=? where id_personne=?";
            PreparedStatement updateSales2 = con.prepareStatement(query2);
            updateSales2.setString(1, description);
            updateSales2.setBoolean(2, Boolean.parseBoolean(actif));
            updateSales2.setInt(3, Integer.parseInt(id));

            ResultSet res2 = updateSales.executeQuery();

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
