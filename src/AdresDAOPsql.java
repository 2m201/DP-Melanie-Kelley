import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class AdresDAOPsql implements AdresDAO {
    Connection myConn = DriverManager.getConnection("jdbc:postgresql:ovchip", "userA", "melanie");
    Statement myStmt = myConn.createStatement();
    ReizigerDAOpsql rdao;

    public AdresDAOPsql(Connection conn) throws SQLException {
    }
    @Override
    public void AdresDAOPsql(Connection conn) {

    }

    @Override
    public boolean save(Adres adres) throws SQLException {
        String q = "INSERT INTO adres (adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id) values (?, ?, ?, ?, ?, ?);";
        PreparedStatement pst = myConn.prepareStatement(q);
        try {
            pst.setInt(1, adres.getAdres_id());
            pst.setString(2, adres.getPostcode());
            pst.setString(3, adres.getHuisnummer());
            pst.setString(4, adres.getStraat());
            pst.setString(5, adres.getWoonplaats());
            pst.setInt(6, adres.getReiziger().getId());
            int rt = pst.executeUpdate();
            return true;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Adres adres) throws SQLException {
        String q = "UPDATE adres SET woonplaats=? WHERE adres_id =?;";
        PreparedStatement pst = myConn.prepareStatement(q);

        try{
            pst.setString(1, "Utrecht");
            pst.setInt(2, adres.getAdres_id());
            int mRst = pst.executeUpdate();
            return true;
        } catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Adres adres) throws SQLException {
        String q = "DELETE FROM adres WHERE adres_id= ?;"; // no need for * after delete, DELETE deletes everything already
        PreparedStatement pst = myConn.prepareStatement(q);

        try{
            pst.setInt(1, adres.getAdres_id());
            int myRs = pst.executeUpdate(); // use executeUpdate() instead of executeQuery() when you are not expecting information back (like SELECT)
            return true;
        }
        catch(Exception e ) {e.printStackTrace();}
        return false;
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) throws SQLException {
        Adres a1 = null;
        String q = "SELECT * FROM adres WHERE reiziger_id = ?;";
        PreparedStatement pst = myConn.prepareStatement(q);
        try{
            pst.setInt(1, reiziger.getId());
            ResultSet myRs= pst.executeQuery();

            while (myRs.next()) {
                a1 = new Adres(myRs.getInt("adres_id"), myRs.getString("postcode"), myRs.getString("huisnummer"), myRs.getString("straat"), myRs.getString("woonplaats"), reiziger);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return a1;
    }

    @Override
    public List<Adres> findAll() throws SQLException {
        rdao = new ReizigerDAOpsql(myConn);
        ArrayList<Adres> alleAdressen = new ArrayList<>();
        ResultSet myRs = myStmt.executeQuery("select * from adres");

        while (myRs.next()) {
            Reiziger r1 = rdao.findById(myRs.getInt("reiziger_id"));
            Adres a1 = new Adres(myRs.getInt("adres_id"), myRs.getString("postcode"), myRs.getString("huisnummer"), myRs.getString("straat"), myRs.getString("woonplaats"), r1);
            r1.setAdres(a1);
            alleAdressen.add(a1);
        }
        return alleAdressen;
    }

    @Override
    public Adres findById(int id) throws SQLException {
        Adres a1 = null;
        String q = "SELECT * FROM adres WHERE adres_id = ?;";
        PreparedStatement pst = myConn.prepareStatement(q);
        try{
            pst.setInt(1, id);
            ResultSet myRs= pst.executeQuery();

            while (myRs.next()) {
                Reiziger r1 = rdao.findById(myRs.getInt("reiziger_id"));
                a1 = new Adres(myRs.getInt("adres_id"), myRs.getString("postcode"), myRs.getString("huisnummer"), myRs.getString("straat"), myRs.getString("woonplaats"), r1);
                r1.setAdres(a1);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return a1;
    } // DONE

}
