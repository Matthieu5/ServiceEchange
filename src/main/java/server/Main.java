package server;

import org.json.JSONObject;
import sql.*;
import xyz.baddeveloper.lwsl.packet.Packet;
import xyz.baddeveloper.lwsl.server.SocketHandler;
import xyz.baddeveloper.lwsl.server.SocketServer;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    private static SocketServer socketServer;
    public static HashMap <String, String> token = new HashMap<String, String>();

    public static void main(String[] args) {

        socketServer = new SocketServer(25566)
                .setMaxConnections(20) // 0 for infinite
                .addConnectEvent(socket -> System.out.println(String.format("Client connected! (%s)", socket.toString())))
                .addDisconnectEvent(socket -> System.out.println(String.format("Client disconnected! (%s)", socket.toString())))
                .addPacketReceivedEvent((socket, packet) -> {
                    try {
                        lireMessage(socket, packet.getObject());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })
                .addReadyEvent(socketServ -> System.out.println(String.format("Socket server is ready for connections! (%s)", socketServ.getServerSocket().toString())))
                .addPacketSentEvent(((socketHandler, packet) -> System.out.println(String.format("Packet sent! (%s)", packet.getObject().toString()))));
        socketServer.start();
    }

    public static void lireMessage(SocketHandler socket, JSONObject message) throws Exception {
        System.out.println(message);

        if(message.getString("typePacket").equals("Login")) {
            ConnectUserSQL cus = new ConnectUserSQL();
            if (cus.getSQLServerConnection(message.getString("username"), message.getString("password")) != "") {
                socket.sendPacket(new LoginPacketReturn("true"));
                token.put(socket.getSocket().getInetAddress().toString().substring(1),cus.getSQLServerConnection(message.getString("username"), message.getString("password")));
                System.out.println(token);
            } else {
                socket.sendPacket(new LoginPacketReturn("false"));
            }
        } else if(message.getString("typePacket").equals("Inscription")) {
            AddUserSQL aus = new AddUserSQL();
            ConnectUserSQL cus = new ConnectUserSQL();

            if(aus.add(message.getString("nom"),message.getString("prenom"),message.getString("telephone"),message.getString("email"),message.getString("adresse"), message.getInt("age"),message.getString("mdp"),message.getString("login")) == true) {
                token.put(socket.getSocket().getInetAddress().toString().substring(1),cus.getSQLServerConnection(message.getString("login"), message.getString("mdp")));
                System.out.println(token);
                socket.sendPacket(new InscriptionPacketReturn("true"));
            } else {
                socket.sendPacket(new InscriptionPacketReturn("false"));
            }
        }else if(message.getString("typePacket").equals("Deconnexion")) {
            token.remove(socket.getSocket().getInetAddress().toString().substring(1));
            socket.sendPacket(new DeconnexionPacketReturn("true"));
            System.out.println("Déconnexion " + token.toString());

        } else if(message.getString("typePacket").equals("DemandeProfil")) {
            SelectProfilSQL sps = new SelectProfilSQL();


            System.out.println(token.get(socket.getSocket().getInetAddress().toString().substring(1)));
            ArrayList infos = sps.getSQLProfil(token.get(socket.getSocket().getInetAddress().toString().substring(1)));
            if(infos.isEmpty()) {
                System.out.println("Aucun profil trouvé");
            } else {
                String nom = String.valueOf(infos.get(0));
                String prenom = String.valueOf(infos.get(1));
                String tel = String.valueOf(infos.get(2));
                String mail = String.valueOf(infos.get(3));
                String adresse = String.valueOf(infos.get(4));
                String age = String.valueOf(infos.get(5));
                String dateInscription = String.valueOf(infos.get(6));
                String moyenne = String.valueOf(infos.get(7));
                String description = String.valueOf(infos.get(8));
                String compteur = String.valueOf(infos.get(9));
                String actif = String.valueOf(infos.get(10));
                String categorie = String.valueOf(infos.get(11));

                socket.sendPacket(new DemandeProfilPacketReturn(nom, prenom, tel, mail, adresse, age, dateInscription, moyenne, description, compteur, actif, categorie));
            }
        } else if(message.getString("typePacket").equals("ModifierProfil")) {
            UpdateProfilSQL ups = new UpdateProfilSQL();
            System.out.println(token.get(socket.getSocket().getInetAddress().toString().substring(1)));
            Boolean retour = ups.updateProfilSQL(token.get(socket.getSocket().getInetAddress().toString().substring(1)), message.getString("nom"), message.getString("prenom"), message.getString("telephone"), message.getString("age"), message.getString("email"), message.getString("adresse"), message.getString("categorie"), message.getString("actif"), message.getString("description"));
            if(retour.equals(true)) {
                socket.sendPacket(new UpdateProfilPacketReturn("true"));
            } else {
                System.out.println("Erreur de modification du profil");
            }
        }else if(message.getString("typePacket").equals("obtenirCategorie")) {
            ObtenirProfil op = new ObtenirProfil();
            HashMap infos = op.getAllProfilsCategorie(message.getString("categorie"),token.get(socket.getSocket().getInetAddress().toString().substring(1)));

            if(infos.isEmpty()) {
                System.out.println("Aucun profil trouvé");
                socket.sendPacket(new AfficherProfilCategoriePacketReturn(new HashMap()));
            } else {
                socket.sendPacket(new AfficherProfilCategoriePacketReturn(infos));
            }
        } else if(message.getString("typePacket").equals("EnvoiMessage")) {
            InsertProfilMessageSQL ipms = new InsertProfilMessageSQL();
            Boolean retour = ipms.getInsertProfilMessageSQL(token.get(socket.getSocket().getInetAddress().toString().substring(1)), message.getString("nom"), message.getString("prenom"), message.getString("message"));


            if(retour.equals(true)) {
                SelectProfilMessageSortantSQL spmss = new SelectProfilMessageSortantSQL();
                ArrayList messagesSortant = spmss.getSelectProfilMessageSortantSQL(token.get(socket.getSocket().getInetAddress().toString().substring(1)), message.getString("nom"), message.getString("prenom"));
                SelectProfilMessageEntrantSQL spmes = new SelectProfilMessageEntrantSQL();
                ArrayList messagesEntrant = spmes.getSelectProfilMessageEntrantSQL(token.get(socket.getSocket().getInetAddress().toString().substring(1)), message.getString("nom"), message.getString("prenom"));

                socket.sendPacket(new MessagePacketReturn(messagesSortant, messagesEntrant));
            } else {
                System.out.println("Erreur Envoi message");
            }
        }  else if(message.getString("typePacket").equals("RecupMessage")) {
            SelectProfilMessageSortantSQL spmss = new SelectProfilMessageSortantSQL();
            ArrayList messagesSortant = spmss.getSelectProfilMessageSortantSQL(token.get(socket.getSocket().getInetAddress().toString().substring(1)), message.getString("nom"), message.getString("prenom"));
            SelectProfilMessageEntrantSQL spmes = new SelectProfilMessageEntrantSQL();
            ArrayList messagesEntrant = spmes.getSelectProfilMessageEntrantSQL(token.get(socket.getSocket().getInetAddress().toString().substring(1)), message.getString("nom"), message.getString("prenom"));

            socket.sendPacket(new MessagePacketReturn(messagesSortant, messagesEntrant));
        } else if (message.getString("typePacket").equals("PropositionPrestation")) {
            InsertPropositionPrestationSQL ipps = new InsertPropositionPrestationSQL();
            Boolean retour = ipps.getPropositionPrestationSQL(token.get(socket.getSocket().getInetAddress().toString().substring(1)), message.getString("nbHeure"), message.getString("descriptionPrestation"), message.getString("nomDestinataire"), message.getString("prenomDestinataire"));
        }else if (message.getString("typePacket").equals("recupPrestation")) {
            RecupPrestation recupPrestation = new RecupPrestation();
            socket.sendPacket(new prestationPacketReturn(recupPrestation.getAllPrestation(token.get(socket.getSocket().getInetAddress().toString().substring(1)))));
        } else if (message.getString("typePacket").equals("RecupUser")) {
            RecupUser recupUser = new RecupUser();
            ArrayList users = recupUser.getUserMessage(token.get(socket.getSocket().getInetAddress().toString().substring(1)));
            FormatUser formatUser = new FormatUser();
            ArrayList usersFormat = formatUser.getUsersFormat(users);
            socket.sendPacket(new userPacketReturn(usersFormat));
        } else if (message.getString("typePacket").equals("EnvoiNote")) {
            EnvoiNote envoiNote = new EnvoiNote();
            envoiNote.insertNote(message.getString("idPresta"), message.getString("notePresta"));
        }
    }

    public static class userPacketReturn extends Packet {

        public userPacketReturn(ArrayList users){
            getObject().put("typePacket", "User retour");
            getObject().put("users", users);
        }
    }

    public static class AfficherProfilCategoriePacketReturn extends Packet {

        public AfficherProfilCategoriePacketReturn(HashMap tableauInfos){
            getObject().put("typePacket", "Profil Categorie retour");
            getObject().put("tableauInfos", tableauInfos);
        }
    }

    public static class InscriptionPacketReturn extends Packet {

        public InscriptionPacketReturn(String reponse){
            getObject().put("typePacket", "Inscription retour");
            getObject().put("message", reponse);
        }
    }

    public static class LoginPacketReturn extends Packet {

        public LoginPacketReturn(String reponse){
            getObject().put("typePacket", "Login retour");
            getObject().put("message", reponse);
        }
    }

    public static class UpdateProfilPacketReturn extends Packet {

        public UpdateProfilPacketReturn(String reponse){
            getObject().put("typePacket", "Update Profil retour");
            getObject().put("message", reponse);
        }
    }

    public static class MessagePacketReturn extends Packet {

        public MessagePacketReturn(ArrayList messagesSortant, ArrayList messagesEntrant){
            getObject().put("typePacket", "Message retour");
            getObject().put("messagesSortant", messagesSortant);
            getObject().put("messagesEntrant", messagesEntrant);
        }
    }

    public static class DeconnexionPacketReturn extends Packet {

        public DeconnexionPacketReturn(String reponse){
            getObject().put("typePacket", "Deconnexion retour");

        }
    }

    public static class DemandeProfilPacketReturn extends Packet {

        public DemandeProfilPacketReturn(String nom, String prenom, String tel, String mail, String adresse, String age, String dateInscription, String moyenne, String description, String compteur, String actif, String categorie){
            getObject().put("typePacket", "Demande Profil retour");
            getObject().put("nom", nom);
            getObject().put("prenom", prenom);
            getObject().put("tel", tel);
            getObject().put("mail", mail);
            getObject().put("adresse", adresse);
            getObject().put("age", age);
            getObject().put("dateInscription", dateInscription);
            getObject().put("moyenne", moyenne);
            getObject().put("description", description);
            getObject().put("compteur", compteur);
            getObject().put("actif", actif);
            getObject().put("categorie", categorie);
        }
    }

    public static class prestationPacketReturn extends Packet {
        public prestationPacketReturn(HashMap tableauPrestation){
            getObject().put("typePacket", "prestation retour");
            getObject().put("tableauPrestation", tableauPrestation);
        }


    }
}
