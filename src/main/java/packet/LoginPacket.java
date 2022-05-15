package packet;

import xyz.baddeveloper.lwsl.packet.Packet;

public class LoginPacket extends Packet {

    public LoginPacket(String typePacket, String username, String password){
        getObject().put("typePacket", typePacket);
        getObject().put("username", username);
        getObject().put("password", password);
    }
}