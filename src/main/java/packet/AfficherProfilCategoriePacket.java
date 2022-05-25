package packet;

import xyz.baddeveloper.lwsl.packet.Packet;

public class AfficherProfilCategoriePacket extends Packet {
    public AfficherProfilCategoriePacket(String typePacket){
        getObject().put("typePacket", typePacket);
    }
}
