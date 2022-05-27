package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class User {
    protected static String nomPrenom;
    protected static ObservableList users;

    public User(String nomPrenom) {
        this.nomPrenom = nomPrenom;
    }

    public static void mettreDansTableau() {
        ObservableList<String> users = FXCollections.observableArrayList();
        users.add(getNomPrenom());
    }

    public static String getNomPrenom() {
        return nomPrenom;
    }

    public static void setNomPrenom(String nomPrenom) {
        User.nomPrenom = nomPrenom;
    }

    public static ObservableList getUsers() {
        return users;
    }
}
