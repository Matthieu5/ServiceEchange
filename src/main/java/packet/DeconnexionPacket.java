package packet;

import xyz.baddeveloper.lwsl.packet.Packet;

public class DeconnexionPacket extends Packet {
    public DeconnexionPacket(String typePacket){
        getObject().put("typePacket", typePacket);
    }


}
