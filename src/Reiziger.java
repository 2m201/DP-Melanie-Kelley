import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reiziger {
    private int id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;
    private Adres adres;
    private List<OVChipkaart> alleChipkaarten = new ArrayList<>();

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
    public Adres getAdres() {
        return adres;
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
    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public boolean addOV(OVChipkaart chipkaart) {
        try {
            alleChipkaarten.add(chipkaart);
            return true;

        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean removeOV(OVChipkaart chipkaart) {
        try {
            alleChipkaarten.remove(chipkaart);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String toString(){
        if (tussenvoegsel == null) {
            return "Reiziger {#" + id + " " + voorletters + ". " + achternaam + ", geb. " + geboortedatum + ", " + "Adres {#" + adres.getAdres_id() + " " + adres.getStraat() + " " + adres.getHuisnummer() + ", " + adres.getWoonplaats() + " " + adres.getPostcode() + "}}";
        } else {
            return "Reiziger {#" + id + " " + voorletters + ". "+ tussenvoegsel + " " + achternaam + ", geb. " + geboortedatum + ", " + "Adres {#" + adres.getAdres_id() + " " + adres.getStraat() + " " + adres.getHuisnummer() + ", " + adres.getWoonplaats() + " " + adres.getPostcode() + "} met OVChipkaarten: " + alleChipkaarten+"}";
        }
    }

    public List<OVChipkaart> getAlleChipkaarten() {
        return alleChipkaarten;
    }
}
