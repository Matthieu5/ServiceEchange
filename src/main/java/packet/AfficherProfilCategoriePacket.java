package packet;

import xyz.baddeveloper.lwsl.packet.Packet;

public class AfficherProfilCategoriePacket extends Packet {
    public AfficherProfilCategoriePacket(String typePacket, String categorie){
        getObject().put("typePacket", typePacket);
        getObject().put("typePacket", categorie);
    }
}
