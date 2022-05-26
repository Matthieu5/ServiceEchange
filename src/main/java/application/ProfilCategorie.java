package application;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class ProfilCategorie {
    protected static JSONArray profils;

    public ProfilCategorie (JSONArray profils) {
        this.profils=profils;
    }
    public ProfilCategorie () {
        this.profils=profils;
    }

    public static JSONArray getProfils() {
        return profils;
    }
    public static void setProfils(JSONArray jsonArray) {
        profils = jsonArray;
    }

}
