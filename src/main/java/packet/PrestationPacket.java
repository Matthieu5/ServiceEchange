package packet;

import xyz.baddeveloper.lwsl.packet.Packet;

public class PrestationPacket extends Packet {
    public PrestationPacket(String typePacket){
        getObject().put("typePacket", typePacket);
    }
}
