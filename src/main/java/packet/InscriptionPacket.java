package packet;

import xyz.baddeveloper.lwsl.packet.Packet;

public class InscriptionPacket extends Packet {
    public InscriptionPacket(String typePacket, String nom, String prenom, String telephone, String email, String adresse, int age, String mdp, String login){
        getObject().put("typePacket", typePacket);
        getObject().put("nom", nom);
        getObject().put("prenom", prenom);
        getObject().put("telephone", telephone);
        getObject().put("email", email);
        getObject().put("adresse", adresse);
        getObject().put("age", age);
        getObject().put("mdp", mdp);
        getObject().put("login", login);
    }
}