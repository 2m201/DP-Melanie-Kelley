import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class OVChipkaartpsql implements OVChipkaartDAO {
    Connection myConn = DriverManager.getConnection("jdbc:postgresql:ovchip", "userA", "melanie");
    Statement myStmt = myConn.createStatement();
    ReizigerDAOpsql rdao = new ReizigerDAOpsql(myConn);
    AdresDAOPsql adao = new AdresDAOPsql(myConn);

    public OVChipkaartpsql(Connection connection) throws SQLException {
    }

    @Override
    public void OVChipkaartDAO(Connection conn) {

    }

    @Override
    public boolean save(OVChipkaart chipkaart) throws SQLException {
        String q = "INSERT INTO ov_chipkaart (kaart_nummer, geldig_tot, klasse, saldo, reiziger_id) values (?, ?, ?, ?, ?);";
        PreparedStatement pst = myConn.prepareStatement(q);
        try {
            pst.setInt(1, chipkaart.getKaart_nummer());
            pst.setDate(2, (Date) chipkaart.getGeldig_tot());
            pst.setInt(3, chipkaart.getKlasse());
            pst.setInt(4, chipkaart.getSaldo());
            pst.setInt(5, chipkaart.getReiziger().getId());
            int rt = pst.executeUpdate();
            pst.close();
            myConn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<OVChipkaart> findall() throws SQLException {
        ArrayList<OVChipkaart> alleOVChipkaarten = new ArrayList<>();
        ResultSet myRs = myStmt.executeQuery("select * from ov_chipkaart");

        while (myRs.next()) {
            Reiziger r1 = rdao.findById(myRs.getInt("reiziger_id"));

            OVChipkaart ov1 = new OVChipkaart(myRs.getInt("kaart_nummer"), myRs.getDate("geldig_tot"), myRs.getInt("klasse"), myRs.getInt("saldo"), r1);
            r1.addOV(ov1);
            alleOVChipkaarten.add(ov1);
        }
        myRs.close();
        myConn.close();
        return alleOVChipkaarten;
    }

    @Override
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) throws SQLException {
        ArrayList<OVChipkaart> alleOVChipkaarten = new ArrayList<>();
        String q = "SELECT * FROM ov_chipkaart WHERE reiziger_id = ?;";
        PreparedStatement pst = myConn.prepareStatement(q);
        try {
            pst.setInt(1, reiziger.getId());
            ResultSet myRs = pst.executeQuery();

            while (myRs.next()) {
                Reiziger r1 = rdao.findById(myRs.getInt("reiziger_id"));

                OVChipkaart ov1 = new OVChipkaart(myRs.getInt("kaart_nummer"), myRs.getDate("geldig_tot"), myRs.getInt("klasse"), myRs.getInt("saldo"), r1);
                r1.addOV(ov1);
                alleOVChipkaarten.add(ov1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        pst.close();
        myConn.close();
        return alleOVChipkaarten;

    }
}
