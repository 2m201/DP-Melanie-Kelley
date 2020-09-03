import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import static java.lang.Integer.parseInt;

public class ReizigerDAOpsql implements ReizigerDAO  {
    Connection myConn = DriverManager.getConnection("jdbc:postgresql:ovchip", "userA", "melanie");
    Statement myStmt = myConn.createStatement();
    AdresDAOPsql adao = new AdresDAOPsql(myConn);


    public ReizigerDAOpsql(Connection conn) throws SQLException {
    }
    @Override
    public void ReizigerDAOPsql(Connection conn) {

    }

    @Override
    public boolean save(Reiziger reiziger) throws SQLException {
        String q = "INSERT INTO reiziger (reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum) values (?, ?, ?, ?, ?);";
        PreparedStatement pst = myConn.prepareStatement(q);
        try {
            pst.setInt(1, reiziger.getId());
            pst.setString(2, reiziger.getVoorletters());
            pst.setString(3, reiziger.getTussenvoegsel());
            pst.setString(4, reiziger.getAchternaam());
            pst.setDate(5, (Date) reiziger.getGeboortedatum());
            int rt = pst.executeUpdate();
            return true;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Reiziger reiziger)throws SQLException {
        String q = "UPDATE reiziger SET achternaam=? WHERE reiziger_id =?;";
        PreparedStatement pst = myConn.prepareStatement(q);

        try{
            pst.setString(1, "Berends");
            pst.setInt(2, reiziger.getId());
            int mRst = pst.executeUpdate();
            return true;
        } catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Reiziger reiziger) throws SQLException {
        String q = "DELETE FROM reiziger WHERE reiziger_id= ?;"; // no need for * after delete, DELETE deletes everything already
        PreparedStatement pst = myConn.prepareStatement(q);

        try{
            pst.setInt(1, reiziger.getId());
            int myRs = pst.executeUpdate(); // use executeUpdate() instead of executeQuery() when you are not expecting information back (like SELECT)
            return true;
        }
        catch(Exception e ) {e.printStackTrace();}
        return false;
    }

    @Override
    public Reiziger findById(int id) throws SQLException {
        Reiziger r1 = null;
        String q = "SELECT * FROM reiziger WHERE reiziger_id = ?;";
        PreparedStatement pst = myConn.prepareStatement(q);
        try{
            pst.setInt(1, id);
            ResultSet myRs= pst.executeQuery();

            while (myRs.next()) {
                r1 = new Reiziger(parseInt(myRs.getString("reiziger_id")), myRs.getString("voorletters"), myRs.getString("tussenvoegsel"), myRs.getString("achternaam"), Date.valueOf(myRs.getString("geboortedatum")));
                Adres a11 = adao.findByReiziger(r1);
                r1.setAdres(a11);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return r1;
    } // DONE

    @Override
    public List<Reiziger> findByGbDatum(String datum) throws SQLException {
        ArrayList<Reiziger> alleReizigers = new ArrayList<>();
        Reiziger r1 = null;

        String q = "SELECT * FROM reiziger WHERE geboortedatum = ?;";
        PreparedStatement pst = myConn.prepareStatement(q);
        try{
            pst.setDate(1, java.sql.Date.valueOf(datum));
            ResultSet myRs= pst.executeQuery();

            while (myRs.next()) {
                r1 = new Reiziger(parseInt(myRs.getString("reiziger_id")), myRs.getString("voorletters"), myRs.getString("tussenvoegsel"), myRs.getString("achternaam"), Date.valueOf(myRs.getString("geboortedatum")));
                Adres a11 = adao.findByReiziger(r1);
                r1.setAdres(a11);
                alleReizigers.add(r1);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return alleReizigers;
    }

    @Override
    public List<Reiziger> findAll() throws SQLException {
        ArrayList<Reiziger> alleReizigers = new ArrayList<>();
        ResultSet myRs = myStmt.executeQuery("select * from reiziger");

        while (myRs.next()) {
            Reiziger r1 = new Reiziger(parseInt(myRs.getString("reiziger_id")), myRs.getString("voorletters"), myRs.getString("tussenvoegsel"), myRs.getString("achternaam"), Date.valueOf(myRs.getString("geboortedatum")));
            Adres a11 = adao.findByReiziger(r1);
            r1.setAdres(a11);
            alleReizigers.add(r1);
        }
        return alleReizigers;
    }

}
