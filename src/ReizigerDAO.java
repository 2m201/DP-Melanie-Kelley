import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ReizigerDAO {

    boolean save(Reiziger reiziger) throws SQLException; //nieuwe reiziger
    boolean update(Reiziger reiziger) throws SQLException;
    boolean delete(Reiziger reiziger) throws SQLException;
    Reiziger findById(int id) throws SQLException;
    List<Reiziger> findByGbDatum(String datum) throws SQLException;
    List<Reiziger> findAll() throws SQLException;

}
