package server;

import org.json.JSONObject;
import packet.DeconnexionPacket;
import sql.AddUserSQL;
import sql.ConnectUserSQL;
import sql.SelectProfilSQL;
import xyz.baddeveloper.lwsl.client.SocketClient;
import xyz.baddeveloper.lwsl.packet.Packet;
import xyz.baddeveloper.lwsl.server.SocketHandler;
import xyz.baddeveloper.lwsl.server.SocketServer;

import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
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

            /*if(sps.getSQLProfil()) {
                socket.sendPacket(new DemandeProfilPacketReturn(infos));
            }*/
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

    public static class DeconnexionPacketReturn extends Packet {

        public DeconnexionPacketReturn(String reponse){
            getObject().put("typePacket", "Deconnexion retour");

        }
    }

    public static class DemandeProfilPacketReturn extends Packet {

        public DemandeProfilPacketReturn(ArrayList reponse){
            getObject().put("typePacket", "Demande Profil retour");
            getObject().put("message", reponse);
        }
    }
}
