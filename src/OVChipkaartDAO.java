import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface OVChipkaartDAO {
    Connection myConn = null;

    void OVChipkaartDAO(Connection conn);

    boolean save(OVChipkaart chipkaart) throws SQLException;
    boolean delete(OVChipkaart o) throws SQLException;
    boolean update(OVChipkaart o) throws  SQLException;
    List<OVChipkaart> findall() throws SQLException;
    List<OVChipkaart> findByReiziger(Reiziger reiziger) throws SQLException;
    boolean deleteProduct(OVChipkaart o, Product p) throws SQLException;
    boolean addProduct(OVChipkaart o, Product p, String status, Date date) throws SQLException;
    List<OVChipkaart> findByProduct(Product p) throws SQLException;

    void setReizigerDAO(ReizigerDAO r);

    ReizigerDAO getReizigerDAO();

    void setProductDAO(ProductDAO r);
    ProductDAO getProductDAO();



}
