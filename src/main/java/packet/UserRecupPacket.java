package packet;

import xyz.baddeveloper.lwsl.packet.Packet;

public class UserRecupPacket extends Packet {
    public UserRecupPacket(String typePacket){
        getObject().put("typePacket", typePacket);
    }
}