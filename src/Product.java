import java.util.ArrayList;
import java.util.List;

public class Product {
    private int product_nummer;
    private String naam;
    private String beschrijving;
    private int prijs;
//    private List<OVChipkaart> alleOVChipkaarten;

    public Product (int pn, String n, String b, int p) {
        this.product_nummer = pn;
        this.naam = n;
        this.beschrijving = b;
        this.prijs = p;
//        alleOVChipkaarten = new ArrayList<>();
    }

    public int getProduct_nummer() {
        return product_nummer;
    }
    public String getNaam() {
        return naam;
    }
    public String getBeschrijving() {
        return beschrijving;
    }
    public int getPrijs() {
        return prijs;
    }

    public void setProduct_nummer(int product_nummer) {
        this.product_nummer = product_nummer;
    }
    public void setNaam(String naam) {
        this.naam = naam;
    }
    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }
    public void setPrijs(int prijs) {
        this.prijs = prijs;
    }


//    public boolean addOV(OVChipkaart chipkaart) {
//        try {
//            alleOVChipkaarten.add(chipkaart);
//            return true;
//
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//    public boolean removeOV(OVChipkaart chipkaart) {
//        try {
//            alleOVChipkaarten.remove(chipkaart);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
}
