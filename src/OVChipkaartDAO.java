import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OVChipkaartDAO {
    Connection myConn = null;

    void OVChipkaartDAO(Connection conn);

    boolean save(OVChipkaart chipkaart) throws SQLException;
    boolean delete(OVChipkaart o) throws SQLException;
    boolean update(OVChipkaart o) throws  SQLException;
    List<OVChipkaart> findall() throws SQLException;
    List<OVChipkaart> findByReiziger(Reiziger reiziger) throws SQLException;

    void setReizigerDAO(ReizigerDAO r);

    ReizigerDAO getReizigerDAO();



}
