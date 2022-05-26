package packet;

import xyz.baddeveloper.lwsl.packet.Packet;

public class PropositionPrestation extends Packet {
    public PropositionPrestation(String typePacket, String nbHeure, String descriptionPrestation, String nomDestinataire, String prenomDestinataire){
        getObject().put("typePacket", typePacket);
        getObject().put("nbHeure", nbHeure);
        getObject().put("descriptionPrestation", descriptionPrestation);
        getObject().put("nomDestinataire", nomDestinataire);
        getObject().put("prenomDestinataire", prenomDestinataire);
    }
}