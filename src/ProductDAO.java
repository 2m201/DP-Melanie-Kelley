import java.sql.Connection;

public interface ProductDAO {
    Connection myConn = null;
    ReizigerDAO rdao = null;

    void ProductDAOpsql(Connection conn);
    boolean save (Product p);
    boolean update(Product p);
    boolean delete(Product p);

}
