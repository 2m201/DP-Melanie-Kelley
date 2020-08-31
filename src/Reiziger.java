import java.util.Date;

public class Reiziger {
    private int id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;

    public String toString(){
        if (tussenvoegsel == null) {
            return "De reiziger " + voorletters + ". " + achternaam + " met het ID " + id + " is geboren op " + geboortedatum;

        } else {
            return "De reiziger " + voorletters + ". " + tussenvoegsel + " " + achternaam + " met het ID " + id + " is geboren op " + geboortedatum;
        }
    }

    public Reiziger(int i, String v, String t, String a, Date g) {
        id = i;
        voorletters = v;
        tussenvoegsel = t;
        achternaam = a;
        geboortedatum = g;

    }

    public int getId(){
        return id;
    }
    public String getVoorletters() {
        return voorletters;
    }
    public String getTussenvoegsel() {
        return tussenvoegsel;
    }
    public String getAchternaam() {
        return achternaam;
    }
    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }
    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }
    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }
    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }
}