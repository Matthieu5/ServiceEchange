package packet;

import xyz.baddeveloper.lwsl.packet.Packet;

public class MessagePacket extends Packet {

    public MessagePacket(String typePacket, String nom, String prenom, String categorie, String message){
        getObject().put("typePacket", typePacket);
        getObject().put("nom", nom);
        getObject().put("prenom", prenom);
        getObject().put("categorie", categorie);
        getObject().put("message", message);
    }
}