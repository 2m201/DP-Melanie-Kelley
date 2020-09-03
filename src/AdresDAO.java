import java.rmi.server.ExportException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface AdresDAO {
    Connection myConn = null;
    ReizigerDAO rdao = null;

    void AdresDAOPsql(Connection conn);
    boolean save(Adres adres) throws SQLException;
    boolean update(Adres adres) throws SQLException;
    boolean delete(Adres adres) throws SQLException;
    Adres findByReiziger(Reiziger reiziger) throws SQLException;
    List<Adres> findAll() throws SQLException;
    public Adres findById(int id) throws SQLException;
}
