import java.util.Date;

public class OVChipkaart {
    private int kaart_nummer;
    private Date geldig_tot;
    private int klasse;
    private int saldo;
    private Reiziger reiziger;

    public OVChipkaart(int kn, Date gt, int k, int s, Reiziger r) {
        kaart_nummer = kn;
        geldig_tot = gt;
        klasse = k;
        saldo = s;
        reiziger = r;
    }

    public int getKaart_nummer() {
        return kaart_nummer;
    }
    public Date getGeldig_tot() {
        return geldig_tot;
    }
    public int getKlasse() {
        return klasse;
    }
    public int getSaldo() {
        return saldo;
    }
    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setKaart_nummer(int kaart_nummer) {
        this.kaart_nummer = kaart_nummer;
    }
    public void setGeldig_tot(Date geldig_tot) {
        this.geldig_tot = geldig_tot;
    }
    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }
    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    @Override
    public String toString() {
        return "OVChipkaart{" +
                "kaart_nummer=" + kaart_nummer +
                ", geldig_tot=" + geldig_tot +
                ", klasse=" + klasse +
                ", saldo=" + saldo +
                ", reiziger_id=" + reiziger.getId() +
                '}';
    }
}

