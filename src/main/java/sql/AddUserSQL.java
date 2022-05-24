package sql;

import java.sql.*;



public class AddUserSQL {
    public boolean add(String nom, String prenom, String tel, String mail,
                    String adresse, int age, String mdp, String login ) throws Exception {

        try {

            //étape 1: charger la classe de driver
            Class.forName("org.postgresql.Driver");

            //étape 2: créer l'objet de connexion
            Connection con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/bd_projetService","postgres","admin");
            System.out.print(nom+" "+ prenom+" "+tel+" "+mail+" "+adresse+" "+age+" "+mdp+" "+login);

            //étape 3: créer l'objet statement
            String query =
                    "INSERT INTO personne(nom_personne, prenom_personne, tel_personne, mail_personne, adresse_personne, age_personne, mdp_personne, login_personne) VALUES (?,?,?,?,?,?,?,?);"+
                    "INSERT INTO utilisateur(date_inscription) VALUES (NOW());";

            PreparedStatement updateSales = con.prepareStatement(query);
            updateSales.setString(1, nom);
            updateSales.setString(2, prenom);
            updateSales.setString(3, tel);
            updateSales.setString(4, mail);
            updateSales.setString(5, adresse);
            updateSales.setInt(6, age);
            updateSales.setString(7, mdp);
            updateSales.setString(8, login);



            //étape 4: exécuter la requête
            updateSales.executeUpdate();

            //étape 5: fermez l'objet de connexion
            con.close();

            return true;
        }
        catch(Exception e) {
            System.out.println(e);
            return false;
        }

    }
}
