package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;



public class ObtenirProfil {
    public HashMap getAllProfilsCategorie(String categorie,String id) throws Exception {
        HashMap profilsConcernes = new HashMap<Integer,ArrayList >();
        ArrayList arrayInfo= new ArrayList<>();



        try {

            //étape 1: charger la classe de driver
            Class.forName("org.postgresql.Driver");

            //étape 2: créer l'objet de connexion
            Connection con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/bd_projetService","postgres","admin");

            //étape 3: créer l'objet statement
            String query = "SELECT nom_personne,prenom_personne,u.description_utilisateur FROM personne p " +
                    "join utilisateur u on p.id_personne=u.id_personne "+
                    "join categorie c on u.id_categorie=c.id_categorie "+
                    "where c.nom_categorie = ? AND u.id_personne != ?";
            PreparedStatement updateSales = con.prepareStatement(query);
            updateSales.setString(1, categorie);
            updateSales.setInt(2, Integer.parseInt(id));
            //étape 4: exécuter la requête
            ResultSet res = updateSales.executeQuery();

            int compteur = 1;

            while(res.next()) {

                arrayInfo.add(0,res.getString(1));
                arrayInfo.add(1,res.getString(2));
                arrayInfo.add(2,res.getString(3));

                profilsConcernes.put(compteur,arrayInfo.clone());

                arrayInfo.clear();


                compteur++;
            }
            //étape 5: fermez l'objet de connexion
            con.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }

        return profilsConcernes;
    }
}
