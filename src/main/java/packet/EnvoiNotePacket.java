package packet;

import xyz.baddeveloper.lwsl.packet.Packet;

public class EnvoiNotePacket extends Packet {
    public EnvoiNotePacket(String typePacket){
        getObject().put("typePacket", typePacket);
    }
}