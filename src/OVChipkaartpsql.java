import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaartpsql implements OVChipkaartDAO {
    Connection conn;
    ReizigerDAO rdao;

    public OVChipkaartpsql(Connection connection) throws SQLException {
        this.conn = connection;
    }

    @Override
    public void OVChipkaartDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean save(OVChipkaart chipkaart) throws SQLException {
        String q = "INSERT INTO ov_chipkaart (kaart_nummer, geldig_tot, klasse, saldo, reiziger_id) values (?, ?, ?, ?, ?);";
        PreparedStatement pst = conn.prepareStatement(q);
        try {
            pst.setInt(1, chipkaart.getKaart_nummer());
            pst.setDate(2, (Date) chipkaart.getGeldig_tot());
            pst.setInt(3, chipkaart.getKlasse());
            pst.setInt(4, chipkaart.getSaldo());
            pst.setInt(5, chipkaart.getReiziger().getId());
            int rt = pst.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            pst.close();
        }
        return false;
    }

    @Override
    public boolean delete(OVChipkaart ov) throws SQLException {
        String q = "DELETE FROM ov_chipkaart WHERE kaart_nummer= ?;"; // no need for * after delete, DELETE deletes everything already
        PreparedStatement pst = conn.prepareStatement(q);

        try{
            pst.setInt(1, ov.getKaart_nummer());
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
    public boolean update(OVChipkaart ov)throws SQLException {
        String q = "UPDATE ov_chipkaart SET geldig_tot=?, klasse=?, saldo=?, reiziger_id=? WHERE kaart_nummer =?;";
        PreparedStatement pst = conn.prepareStatement(q);

        try{
            pst.setDate(1, (Date) ov.getGeldig_tot());
            pst.setInt(2, ov.getKlasse());
            pst.setInt(3, ov.getSaldo());
            pst.setInt(4, ov.getReiziger().getId());
            pst.setInt(5, ov.getKaart_nummer());
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
    public List<OVChipkaart> findall() throws SQLException {
        List<OVChipkaart> alleOVChipkaarten = new ArrayList<>();
        String q = "select * from ov_chipkaart;";
        PreparedStatement pst = conn.prepareStatement(q);

        try {
            ResultSet myRs= pst.executeQuery();
            while (myRs.next()) {
                Reiziger r1 = rdao.findById(myRs.getInt("reiziger_id"));

                OVChipkaart ov1 = new OVChipkaart(myRs.getInt("kaart_nummer"), myRs.getDate("geldig_tot"), myRs.getInt("klasse"), myRs.getInt("saldo"), r1);

                alleOVChipkaarten.add(ov1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pst.close();
        }
        return alleOVChipkaarten;
    }

    @Override
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) throws SQLException {
        ArrayList<OVChipkaart> alleOVChipkaarten = new ArrayList<>();
        String q = "SELECT * FROM ov_chipkaart WHERE reiziger_id = ?;";
        PreparedStatement pst = conn.prepareStatement(q);
        try {
            pst.setInt(1, reiziger.getId());
            ResultSet myRs = pst.executeQuery();

            while (myRs.next()) {
                OVChipkaart ov1 = new OVChipkaart(myRs.getInt("kaart_nummer"), myRs.getDate("geldig_tot"), myRs.getInt("klasse"), myRs.getInt("saldo"), reiziger);

                alleOVChipkaarten.add(ov1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pst.close();
        }
        return alleOVChipkaarten;
    }

    @Override
    public void setReizigerDAO(ReizigerDAO r) {
        this.rdao = r;
    }

    @Override
    public ReizigerDAO getReizigerDAO() {
        return rdao;
    }


}
