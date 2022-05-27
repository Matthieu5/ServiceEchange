package packet;

import xyz.baddeveloper.lwsl.packet.Packet;

public class AccepterPrestationPacket extends Packet {
    public AccepterPrestationPacket(String typePacket,int id){
        getObject().put("typePacket", typePacket);
        getObject().put("id", id);
    }
}
