package application;

import java.util.ArrayList;
import java.util.HashMap;

public class ProfilCategorie {
    protected static HashMap <String, ArrayList> profils;

    public ProfilCategorie (HashMap profils) {
        this.profils=profils;
    }
    public static HashMap<String, ArrayList> getProfils() {
        return profils;
    }
    public void setProfils(HashMap<String, ArrayList> profils) {
        this.profils = profils;
    }
}
