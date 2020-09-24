import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface ProductDAO {
    Connection myConn = null;

    boolean save (OVChipkaart o, Product p, String status, Date date) throws SQLException;
    boolean update(OVChipkaart o, Product p, String status, Date date) throws SQLException;
    boolean delete(Product p) throws SQLException;

    boolean deleteOVChipkaart(OVChipkaart ov, Product p) throws SQLException;
    boolean addOVChipkaart(OVChipkaart o, Product p, String status, Date date) throws SQLException;
    List<Product> findAll() throws SQLException;

    void setOVChipkaartDAO(OVChipkaartDAO r);
    List<Product> findByOVChipkaart(OVChipkaart o) throws SQLException;

    OVChipkaartDAO getOVCHipkaartDAO();


}
