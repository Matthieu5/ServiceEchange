package packet;

import javafx.scene.control.TextField;
import xyz.baddeveloper.lwsl.packet.Packet;

public class DemandeProfil extends Packet {
    public DemandeProfil(String typePacket){
        getObject().put("typePacket", typePacket);
    }
}