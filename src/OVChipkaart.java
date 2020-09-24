import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OVChipkaart {
    private int kaart_nummer;
    private Date geldig_tot;
    private int klasse;
    private int saldo;
    private Reiziger reiziger;
    private List<Product> alleProducten;

    public OVChipkaart(int kn, Date gt, int k, int s, Reiziger r) {
        kaart_nummer = kn;
        geldig_tot = gt;
        klasse = k;
        saldo = s;
        reiziger = r;
        alleProducten = new ArrayList<>();
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

    public boolean addProduct(Product p) {
        try {
            alleProducten.add(p);
            return true;

        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean removeProduct(Product p) {
        try {
            alleProducten.remove(p);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public String toString() {
        StringBuilder st = new StringBuilder("OVChipkaart: " +
                "kaart_nummer=" + kaart_nummer +
                ", geldig_tot=" + geldig_tot +
                ", klasse=" + klasse +
                ", saldo=" + saldo +
                ", reiziger_id=" + reiziger.getId() +
                ", alleProducten=" );

        for (Product p : alleProducten) {
            st.append(p) ;
            st.append(", ");
        }
        return st.toString();

    }
}

