import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OVChipkaartDAO {
    Connection myConn = null;

    void OVChipkaartDAO(Connection conn);

    boolean save(OVChipkaart chipkaart) throws SQLException;
    List<OVChipkaart> findall() throws SQLException;
    List<OVChipkaart> findByReiziger(Reiziger reiziger) throws SQLException;

    void setAdresDAO(AdresDAO a);
    void setReizigerDAO(ReizigerDAO r);



}
