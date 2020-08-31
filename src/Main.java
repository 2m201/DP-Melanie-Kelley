
import org.w3c.dom.ls.LSOutput;

import java.sql.*;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
       testReizigerDAO(new ReizigerDAOpsql(Main.getConnection()));

    }
//    public static void main(String[] args) {
//        int number = 1;
//
//        try{
//            //1. Get connection to database
//            Connection myConn = DriverManager.getConnection("jdbc:postgresql:ovchip", "melanie", "Cookie20012");
//            //2. Create a statement
//            Statement myStmt = myConn.createStatement();
//            //3. Execute SQL query
//            ResultSet myRs = myStmt.executeQuery("select * from reiziger");
//            //4. Process result set
//
//            System.out.println("Alle reizigers: ");
//            while (myRs.next()) {
//                if (myRs.getString("tussenvoegsel") == null) {
//                    System.out.println("#"+ number + ": " + myRs.getString("voorletters") + ". " + myRs.getString("achternaam") + " (" + myRs.getString("geboortedatum") + ")");
//
//                } else {
//                    System.out.println("#"+ number + ": " + myRs.getString("voorletters") + ". "  + myRs.getString("tussenvoegsel") + " "  + myRs.getString("achternaam") + " (" + myRs.getString("geboortedatum") + ")");
//                }
//                number +=1;
//            }
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//
//    }

    private static Connection getConnection() throws SQLException {
        Connection myConn = DriverManager.getConnection("jdbc:postgresql:ovchip", "userA", "melanie");
        return myConn;
    }
    private static void closeConnection() throws SQLException {
        Connection myConn = DriverManager.getConnection("jdbc:postgresql:ovchip", "userA", "melanie");
        myConn.close();
    }

    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        // Haal alle reizigers op uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

        System.out.println("[Test] ReizigerDAO.findById(2) geeft de volgende reiziger: ");
        System.out.println(rdao.findById(2));

        System.out.println();

//        Reiziger boris = new Reiziger(100, "B", "", "Gravens", java.sql.Date.valueOf("2002-09-17"));
//        rdao.save(boris);

        // Maak een nieuwe reiziger aan en persisteer deze in de database
        String gbdatum = "1981-03-14";
        Reiziger sietske = new Reiziger(77, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        System.out.println();

        System.out.println("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.delete(77) ");
        rdao.delete(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        System.out.println();

        System.out.println("[Test] ReizigerDAO.findByGbDatum(2002-09-17) geeft de volgende reizigers: ");
        System.out.println(rdao.findByGbDatum("2002-09-17"));

        System.out.println();

        String naam = rdao.findById(100).getAchternaam();
        System.out.println("[Test] Eerst had de persoon met ID 100 de achternaam " + naam + ", na ReizigerDAO.update()");
        rdao.update(rdao.findById(100));
        naam = rdao.findById(100).getAchternaam();
        System.out.println(" heeft hij de achternaam " + naam);

        Main.closeConnection();

        // Voeg aanvullende tests van de ontbrekende CRUD-operaties in.
    }

}
