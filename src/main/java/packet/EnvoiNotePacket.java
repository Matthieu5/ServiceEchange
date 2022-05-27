package packet;

import xyz.baddeveloper.lwsl.packet.Packet;

public class EnvoiNotePacket extends Packet {
    public EnvoiNotePacket(String typePacket, String id, String note){
        getObject().put("typePacket", typePacket);
        getObject().put("idPresta", id);
        getObject().put("notePresta", note);
    }
}