package packet;

import xyz.baddeveloper.lwsl.packet.Packet;

public class MessageRecupPacket extends Packet {

    public MessageRecupPacket(String typePacket, String nom, String prenom, String categorie){
        getObject().put("typePacket", typePacket);
        getObject().put("nom", nom);
        getObject().put("prenom", prenom);
        getObject().put("categorie", categorie);
    }
}