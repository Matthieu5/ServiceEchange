package application;

public class Profil {
    protected static String nom;
    protected static String prenom;
    protected static String tel;
    protected static String age;
    protected static String mail;
    protected static String adresse;
    protected static String categorie;
    protected static String actif;
    protected static String dateInscription;
    protected static String note;
    protected static String compteur;
    protected static String description;

    public Profil(String nom, String prenom, String age, String tel, String mail, String adresse, String actif, String categorie, String dateInscription, String note, String compteur, String description) {
        this.dateInscription = dateInscription;
        this.note = note;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.tel = tel;
        this.mail = mail;
        this.adresse = adresse;
        this.actif = actif;
        this.categorie = categorie;
        this.compteur = compteur;
        this.description = description;
    }

    public static String getDescription() {
        return description;
    }

    public static void setDescription(String description) {
        Profil.description = description;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setActif(String actif) {
        this.actif = actif;
    }

    public void setDateInscription(String dateInscription) {
        this.dateInscription = dateInscription;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setCompteur(String compteur) {
        this.compteur = compteur;
    }

    public static String getCompteur() {
        return compteur;
    }

    public static String getDateInscription() {
        return dateInscription;
    }

    public static String getNote() {
        return note;
    }

    public static String getNom() {
        return nom;
    }

    public static String getPrenom() {
        return prenom;
    }

    public static String getAge() {
        return age;
    }

    public static String getTel() {
        return tel;
    }

    public static String getMail() {
        return mail;
    }

    public static String getAdresse() {
        return adresse;
    }

    public static String getActif() {
        return actif;
    }

    public static String getCategorie() {
        return categorie;
    }

    @Override
    public String toString() {
        return "Profil{" +
                "dateInscription='" + dateInscription + '\'' +
                ", note='" + note + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", age='" + age + '\'' +
                ", tel='" + tel + '\'' +
                ", mail='" + mail + '\'' +
                ", adresse='" + adresse + '\'' +
                ", actif='" + actif + '\'' +
                ", categorie='" + categorie + '\'' +
                '}';
    }
}
