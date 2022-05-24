package packet;

import xyz.baddeveloper.lwsl.packet.Packet;

public class ProfilPacket extends Packet {
    public ProfilPacket(String typePacket, String nom, String prenom, String age, String telephone, String email, String adresse, String actif, String description){
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