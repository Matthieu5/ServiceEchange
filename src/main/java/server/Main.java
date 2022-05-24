package server;

import org.json.JSONObject;
import sql.AddUserSQL;
import sql.ConnectUserSQL;
import sql.SelectProfilSQL;
import sql.UpdateProfilSQL;
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

            if(aus.add(message.getString("nom"),message.getString("prenom"),message.getString("telephone"),message.getString("email"),message.getString("adresse"), message.getInt("age"),message.getString("mdp"),message.getString("login")) == true) {
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

                socket.sendPacket(new DemandeProfilPacketReturn(nom, prenom, tel, mail, adresse, age, dateInscription, moyenne, description, compteur, actif));
            }
        } else if(message.getString("typePacket").equals("ModifierProfil")) {
            UpdateProfilSQL ups = new UpdateProfilSQL();
            System.out.println(token.get(socket.getSocket().getInetAddress().toString().substring(1)));
            Boolean retour = ups.updateProfilSQL(token.get(socket.getSocket().getInetAddress().toString().substring(1)), message.getString("nom"), message.getString("prenom"), message.getString("telephone"), message.getString("age"), message.getString("email"), message.getString("adresse"), message.getString("description"), message.getString("actif"));
            if(retour.equals(true)) {
                socket.sendPacket(new UpdateProfilPacketReturn("true"));
            } else {
                System.out.println("Erreur de modification du profil");
            }
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

    public static class DeconnexionPacketReturn extends Packet {

        public DeconnexionPacketReturn(String reponse){
            getObject().put("typePacket", "Deconnexion retour");

        }
    }

    public static class DemandeProfilPacketReturn extends Packet {

        public DemandeProfilPacketReturn(String nom, String prenom, String tel, String mail, String adresse, String age, String dateInscription, String moyenne, String description, String compteur, String actif){
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
        }
    }
}
