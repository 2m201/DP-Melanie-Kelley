
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductDAOpsql implements ProductDAO {
    Connection conn;
    OVChipkaartDAO odao;

    public ProductDAOpsql (Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean save(OVChipkaart o, Product p, String status, Date date) throws SQLException {
        String q = "INSERT INTO product (product_nummer, naam, beschrijving, prijs) VALUES (?, ?, ?, ?);";
        String q2 = "INSERT INTO ov_chipkaart_product (kaart_nummer, product_nummer, status, last_update) VALUES (?, ?, ?, ?);";

        PreparedStatement pst = conn.prepareStatement(q);
        PreparedStatement pt = conn.prepareStatement(q2);

        try {
            pst.setInt(1, p.getProduct_nummer());
            pst.setString(2, p.getNaam());
            pst.setString(3, p.getBeschrijving());
            pst.setInt(4, p.getPrijs());
            pst.executeUpdate();

            pt.setInt(1, o.getKaart_nummer());
            pt.setInt(2, p.getProduct_nummer());
            pt.setString(3, status);
            pt.setDate(4, (java.sql.Date) date);
            pt.executeUpdate();

            return true;

        } catch (Exception e ) {
            e.printStackTrace();
        } finally {
            pst.close();
            pt.close();
        }
        return false;
    } // works

    @Override
    public boolean update(OVChipkaart o, Product p, String status, Date date) throws SQLException {
        String q2 = "DELETE FROM ov_chipkaart_product WHERE product_nummer =? AND kaart_nummer =?";
        String q = "UPDATE product SET naam=?, beschrijving=?, prijs=? WHERE product_nummer = ?;";
        String q3 = "INSERT INTO ov_chipkaart_product (kaart_nummer, product_nummer, status, last_update) VALUES (?, ?, ?, ?);";

        PreparedStatement pst = conn.prepareStatement(q);
        PreparedStatement pst2 = conn.prepareStatement(q2);
        PreparedStatement pst3 = conn.prepareStatement(q3);

        try{
            pst.setString(1, p.getNaam());
            pst.setString(2, p.getBeschrijving());
            pst.setInt(3, p.getPrijs());
            pst.setInt(4, p.getProduct_nummer());

            pst2.setInt(1, p.getProduct_nummer());
            pst2.setInt(2, o.getKaart_nummer());

            pst3.setInt(1, o.getKaart_nummer());
            pst3.setInt(2, p.getProduct_nummer());
            pst3.setString(3, status);
            pst3.setDate(4, (java.sql.Date) date);

            pst2.executeUpdate();
            pst.executeUpdate();
            pst3.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            pst.close();
            pst2.close();
            pst3.close();
        }
        return false;
    } // works (for a specific card)

    @Override
    public boolean delete(Product p) throws SQLException {
        String q = "DELETE FROM product WHERE product_nummer =? ;";
        String q2 = "DELETE FROM ov_chipkaart_product WHERE product_nummer =?";
        PreparedStatement pst = conn.prepareStatement(q);
        PreparedStatement pt = conn.prepareStatement(q2);

        try {
            pt.setInt(1, p.getProduct_nummer());
            pt.executeUpdate();

            pst.setInt(1, p.getProduct_nummer());
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            pst.close();
            pt.close();
        }
        return false;
    } //works

    @Override
    public boolean deleteOVChipkaart(OVChipkaart ov, Product p) throws SQLException {
        p.removeOV(ov);

        String q = "DELETE FROM ov_chipkaart_product WHERE kaart_nummer = ? AND product_nummer = ?;";
        PreparedStatement pst = conn.prepareStatement(q);

        try{
            pst.setInt(1, ov.getKaart_nummer());
            pst.setInt(2, p.getProduct_nummer());
            pst.executeUpdate();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            pst.close();
        }
        return false;
    }

    @Override
    public boolean addOVChipkaart(OVChipkaart o, Product p, String status, Date date) throws SQLException {
        p.addOV(o);

        String q = "INSERT INTO ov_chipkaart_product (kaart_nummer, product_nummer, status, last_update) VALUES (?, ?, ?, ?);";
        PreparedStatement pst = conn.prepareStatement(q);
        try{
            pst.setInt(1, o.getKaart_nummer());
            pst.setInt(2, p.getProduct_nummer());
            pst.setString(3, status);
            pst.setDate(4, (java.sql.Date) date);
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pst.close();
        }
        return false;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> alleProducten = new ArrayList<>();
        String q = "select * from product;";
        PreparedStatement pst = conn.prepareStatement(q);

        try {
            ResultSet myRs= pst.executeQuery();
            while (myRs.next()) {
                Product p = new Product(myRs.getInt("product_nummer"), myRs.getString("naam"), myRs.getString("beschrijving"), myRs.getInt("prijs"));

                alleProducten.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pst.close();
        }
        return alleProducten;
    }

    @Override
    public void setOVChipkaartDAO(OVChipkaartDAO r) {
        this.odao = r;

    }

    @Override
    public List<Product> findByOVChipkaart(OVChipkaart o) throws SQLException {
        String q = "SELECT * FROM product p JOIN ov_chipkaart_product ocp ON p.product_nummer = ocp.product_nummer JOIN ov_chipkaart ov ON ocp.kaart_nummer = ov.kaart_nummer WHERE ov.kaart_nummer = ?;";

        List<Product> alleProducten = new ArrayList<>();

        PreparedStatement pst = conn.prepareStatement(q);

        try {
            pst.setInt(1, o.getKaart_nummer());

            ResultSet myRs = pst.executeQuery();

            while (myRs.next()) {
                Product p = new Product(myRs.getInt("product_nummer"), myRs.getString("naam"), myRs.getString("beschrijving"), myRs.getInt("prijs"));

                alleProducten.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            pst.close();
        }
        return alleProducten;
    }

    @Override
    public OVChipkaartDAO getOVCHipkaartDAO() {
        return this.odao;
    }


}
