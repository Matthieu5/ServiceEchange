package packet;

import xyz.baddeveloper.lwsl.packet.Packet;

public class MessageRecupPacket extends Packet {

    public MessageRecupPacket(String typePacket, String nom, String prenom){
        getObject().put("typePacket", typePacket);
        getObject().put("nom", nom);
        getObject().put("prenom", prenom);
    }
}