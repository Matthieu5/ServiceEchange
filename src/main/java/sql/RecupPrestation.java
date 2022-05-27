package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class RecupPrestation {
    public HashMap getAllPrestation(String id) throws Exception {
        HashMap prestationsRetournees = new HashMap<Integer, ArrayList>();
        ArrayList arrayInfo=new ArrayList<>();
        try {

            //étape 1: charger la classe de driver
            Class.forName("org.postgresql.Driver");

            //étape 2: créer l'objet de connexion
            Connection con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/bd_projetService","postgres","admin");

            //étape 3: créer l'objet statement
            String query = "SELECT date_prestation,realise,accepte,description_prestation,note_prestation,c.nom_categorie,pers.nom_personne FROM prestation p " +
                    "join utilisateur u on p.id_personne=u.id_personne "+
                    "join personne pers on pers.id_personne=u.id_personne "+
                    "join categorie c on u.id_categorie=c.id_categorie "+
                    "where p.id_personne = ? OR p.id_personne_1 = ?";
            PreparedStatement updateSales = con.prepareStatement(query);
            updateSales.setInt(1, Integer.parseInt(id));
            updateSales.setInt(2, Integer.parseInt(id));

            //étape 4: exécuter la requête
            ResultSet res = updateSales.executeQuery();

            int compteur = 1;

            while(res.next()) {
                arrayInfo.add(0,res.getString(1));
                arrayInfo.add(1,res.getString(2));
                arrayInfo.add(2,res.getString(3));
                arrayInfo.add(3,res.getString(4));
                arrayInfo.add(4,res.getString(5));
                arrayInfo.add(5,res.getString(6));

                prestationsRetournees.put(compteur,arrayInfo.clone());

                arrayInfo.clear();
                compteur++;
            }
            //étape 5: fermez l'objet de connexion
            con.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }

        return prestationsRetournees;
    }
}
