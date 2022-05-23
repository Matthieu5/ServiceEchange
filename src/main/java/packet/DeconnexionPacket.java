package packet;

import xyz.baddeveloper.lwsl.packet.Packet;

public class DeconnexionPacket extends Packet {
    public DeconnexionPacket(String typePacket, String id){
        getObject().put("typePacket", typePacket);
        getObject().put("id", id);
    }


}
