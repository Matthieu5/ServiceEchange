package packet;

import javafx.scene.control.TextField;
import xyz.baddeveloper.lwsl.packet.Packet;

public class ProfilPacket extends Packet {
    public ProfilPacket(String typePacket, TextField nom, TextField prenom, TextField age, TextField telephone, TextField email, TextField adresse, TextField actif, TextField description){
        getObject().put("typePacket", typePacket);
        getObject().put("nom", nom);
        getObject().put("prenom", prenom);
        getObject().put("age", age);
        getObject().put("telephone", telephone);
        getObject().put("email", email);
        getObject().put("adresse", adresse);
        getObject().put("actif", actif);
        getObject().put("description", description);
    }
}