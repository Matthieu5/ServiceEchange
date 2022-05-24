package packet;

import xyz.baddeveloper.lwsl.packet.Packet;

public class ModifProfilPacket extends Packet {
    public ModifProfilPacket(String typePacket, String nom, String prenom, String telephone, String age, String email, String adresse, String description, String actif){
        getObject().put("typePacket", typePacket);
        getObject().put("nom", nom);
        getObject().put("prenom", prenom);
        getObject().put("telephone", telephone);
        getObject().put("age", age);
        getObject().put("email", email);
        getObject().put("adresse", adresse);
        getObject().put("description", description);
        getObject().put("actif", actif);
    }
}