public class Adres {
    private int adres_id;
    private String postcode;
    private String huisnummer;
    private String straat;
    private String woonplaats;
    private Reiziger reiziger;

    public Adres(int id, String p, String h, String s, String w, Reiziger r) {
        adres_id = id;
        postcode = p;
        huisnummer = h;
        straat = s;
        woonplaats = w;
        reiziger = r;
    }

    public int getAdres_id() {
        return adres_id;
    }
    public String getPostcode() {
        return postcode;
    }
    public String getHuisnummer() {
        return huisnummer;
    }
    public String getStraat() {
        return straat;
    }
    public String getWoonplaats() {
        return woonplaats;
    }
    public Reiziger getReiziger() {
        return reiziger;
    }
    public String toString(){
        return reiziger.toString();
    }
}
