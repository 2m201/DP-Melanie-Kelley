import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import static java.lang.Integer.parseInt;

public class ReizigerDAOpsql implements ReizigerDAO  {
    Connection conn;
    AdresDAO adao;
    OVChipkaartDAO odao;

    public ReizigerDAOpsql(Connection conn) throws SQLException {
        this.conn = conn;
    }
    @Override
    public void ReizigerDAOPsql(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean save(Reiziger reiziger) throws SQLException {
        String q = "INSERT INTO reiziger (reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum) values (?, ?, ?, ?, ?);";
        PreparedStatement pst = conn.prepareStatement(q);
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
        }finally {
            pst.close();
        }
        return false;
    }

    @Override
    public boolean update(Reiziger reiziger)throws SQLException {
        String q = "UPDATE reiziger SET voorletters=?, tussenvoegsel=?, achternaam=?, geboortedatum=? WHERE reiziger_id =?;";
        PreparedStatement pst = conn.prepareStatement(q);

        try{
            pst.setString(1, reiziger.getVoorletters());
            pst.setString(2, reiziger.getTussenvoegsel());
            pst.setString(3, reiziger.getAchternaam());
            pst.setDate(4, (Date) reiziger.getGeboortedatum());
            pst.setInt(5, reiziger.getId());
            int mRst = pst.executeUpdate();
            return true;
        } catch(Exception e){
            e.printStackTrace();
        }finally {
            pst.close();
        }
        return false;
    }

    @Override
    public boolean delete(Reiziger reiziger) throws SQLException {
        String q = "DELETE FROM reiziger WHERE reiziger_id= ?;"; // no need for * after delete, DELETE deletes everything already
        PreparedStatement pst = conn.prepareStatement(q);

        try{
            pst.setInt(1, reiziger.getId());
            int myRs = pst.executeUpdate(); // use executeUpdate() instead of executeQuery() when you are not expecting information back (like SELECT)
            pst.close();
            return true;
        }
        catch(Exception e ) {e.printStackTrace();
        } finally {
            pst.close();
        }
        return false;
    }

    @Override
    public Reiziger findById(int id) throws SQLException {
        Reiziger r1 = null;
        String q = "SELECT * FROM reiziger WHERE reiziger_id = ?;";
        PreparedStatement pst = conn.prepareStatement(q);
        try{
            pst.setInt(1, id);
            ResultSet myRs= pst.executeQuery();

            while (myRs.next()) {
                r1 = new Reiziger(parseInt(myRs.getString("reiziger_id")), myRs.getString("voorletters"), myRs.getString("tussenvoegsel"), myRs.getString("achternaam"), Date.valueOf(myRs.getString("geboortedatum")));
                r1.setAdres(adao.findByReiziger(r1));
                List<OVChipkaart> kaart = odao.findByReiziger(r1);
                r1.addOVChipkaartList(kaart);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            pst.close();
        }
        return r1;
    } // DONE

    @Override
    public List<Reiziger> findByGbDatum(String datum) throws SQLException {
        ArrayList<Reiziger> alleReizigers = new ArrayList<>();
        Reiziger r1 = null;

        String q = "SELECT * FROM reiziger WHERE geboortedatum = ?;";
        PreparedStatement pst = conn.prepareStatement(q);
        try{
            pst.setDate(1, java.sql.Date.valueOf(datum));
            ResultSet myRs= pst.executeQuery();

            while (myRs.next()) {
                r1 = new Reiziger(parseInt(myRs.getString("reiziger_id")), myRs.getString("voorletters"), myRs.getString("tussenvoegsel"), myRs.getString("achternaam"), Date.valueOf(myRs.getString("geboortedatum")));
                r1.setAdres(adao.findByReiziger(r1));
                r1.addOVChipkaartList(odao.findByReiziger(r1));
                alleReizigers.add(r1);
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            pst.close();
        }
        return alleReizigers;
    }

    @Override
    public List<Reiziger> findAll() throws SQLException {
        ArrayList<Reiziger> alleReizigers = new ArrayList<>();
        String q = "select * from reiziger;";
        PreparedStatement pst =conn.prepareStatement(q);

        try {
            ResultSet myRs= pst.executeQuery();
            while (myRs.next()) {
                Reiziger r1 = new Reiziger(parseInt(myRs.getString("reiziger_id")), myRs.getString("voorletters"), myRs.getString("tussenvoegsel"), myRs.getString("achternaam"), Date.valueOf(myRs.getString("geboortedatum")));
                r1.setAdres(adao.findByReiziger(r1));
                r1.addOVChipkaartList(odao.findByReiziger(r1));
                alleReizigers.add(r1);

            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            pst.close();
        }
        return alleReizigers;
    }

    @Override
    public void setAdresDAO(AdresDAO a) {
        this.adao = a;
    }

    @Override
    public void setOVChipkaartDAO(OVChipkaartDAO o) {
        this.odao = o;
    }

}
