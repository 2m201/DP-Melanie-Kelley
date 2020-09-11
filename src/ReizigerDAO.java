import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ReizigerDAO {
    Connection myConn = null;
    ReizigerDAO rdao = null;

    void ReizigerDAOPsql (Connection conn);

    boolean save(Reiziger reiziger) throws SQLException; //nieuwe reiziger
    boolean update(Reiziger reiziger) throws SQLException;
    boolean delete(Reiziger reiziger) throws SQLException;
    Reiziger findById(int id) throws SQLException;
    List<Reiziger> findByGbDatum(String datum) throws SQLException;
    List<Reiziger> findAll() throws SQLException;

    void setAdresDAO(AdresDAO a);
    void setOVChipkaartDAO(OVChipkaartDAO o);

}
