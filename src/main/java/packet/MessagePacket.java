package packet;

import xyz.baddeveloper.lwsl.packet.Packet;

public class MessagePacket extends Packet {

    public MessagePacket(String typePacket, String message){
        getObject().put("typePacket", typePacket);
        getObject().put("message", message);
    }
}