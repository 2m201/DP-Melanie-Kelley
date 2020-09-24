import java.sql.*;
import java.util.List;

public class Main {

    public Main() throws SQLException {
    }

    public static void main(String[] args) throws SQLException {
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql:ovchip", "userA", "melanie")) {
            AdresDAO adao1 = new AdresDAOPsql(conn);
            OVChipkaartDAO odao = new OVChipkaartpsql(conn);
            ReizigerDAO rdao = new ReizigerDAOpsql(conn);
            ProductDAO pdao = new ProductDAOpsql(conn);

            rdao.setAdresDAO(adao1);
            rdao.setOVChipkaartDAO(odao);

            adao1.setReizigerDAO(rdao);
            adao1.setOVChipkaartDAO(odao);

            odao.setReizigerDAO(rdao);
            odao.setProductDAO(pdao);

            pdao.setOVChipkaartDAO(odao);

//       testReizigerDAO(new ReizigerDAOpsql(conn));
//       testAdresDAO(new AdresDAOPsql(conn));
//            testOVChipkaartDAO(odao);
            testProductDAO(pdao);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql:ovchip", "userA", "melanie");
    }

    private static void closeConnection() throws SQLException {
        Main.getConnection().close();
    }

    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");
        System.out.println();
        System.out.println();

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

    }

    private static void testAdresDAO(AdresDAO adao) throws SQLException {
        ReizigerDAO rdao = new ReizigerDAOpsql(Main.getConnection());
        Reiziger sietske;

        System.out.println("\n---------- Test AdresDAO -------------");
        List<Adres> alleAdressen = adao.findAll();

        System.out.println();

        System.out.println("[Test] AdresDAO.findAll() geeft de volgende adressen:");
        for (Adres r : alleAdressen) {
            System.out.println(r);
        }

        System.out.println();

        System.out.println("[Test] AdresDAO.findById(2) geeft het volgende adres: ");
        System.out.println(adao.findById(2));

        System.out.println();

        String gbdatum = "1981-03-14";
        sietske = new Reiziger(23, "A", "", "C", java.sql.Date.valueOf(gbdatum));
        rdao.save(sietske);
        Adres adres = new Adres(45, "3333aa", "12", "ABC", "hier", sietske);
        System.out.print("[Test] Eerst " + alleAdressen.size() + " adressen, na AdresDAO.save() ");
        sietske.setAdres(adres);
        adao.save(adres);
        alleAdressen = adao.findAll();
        System.out.println(alleAdressen.size() + " adressen\n");

        System.out.println();

        System.out.println("[Test] Eerst " + alleAdressen.size() + " adressen, na AdresDAO.delete() ");
        adao.delete(adao.findById(45));

        alleAdressen = adao.findAll();
        System.out.println(alleAdressen.size() + " adressen\n");
        System.out.println();

        Adres adres4 = adao.findById(3);
        System.out.println("[Test] Eerst heeft het adres van H. Lubben de woonplaats " + adres4.getWoonplaats() + ", na AdresDAO.update()");
        adao.update(adres4);
        adao.save(adres4);
        System.out.println(" heeft het adres woonplaats " + adres4.getWoonplaats());

//        Reiziger melanie = new Reiziger(103, "M", "", "Kelley", java.sql.Date.valueOf(gbdatum));
//
//        Adres a1 = new Adres(6, "3372EN", "3", "Prinses Margrietstraat", "Hardinxveld-Giessendam", melanie);
//
//        melanie.setAdres(a1);


    }

    public static void testOVChipkaartDAO(OVChipkaartDAO odao) throws SQLException {

        Reiziger r1 = new Reiziger(5, "M", null, "K", java.sql.Date.valueOf("2001-12-21"));
        Adres adres = new Adres(45, "3333aa", "12", "ABC", "hier", r1);
        OVChipkaart o = new OVChipkaart(333, java.sql.Date.valueOf("2001-12-21"), 1, 40, r1 );
        r1.setAdres(adres);
        r1.addOV(o);

        List<OVChipkaart> ov = odao.findall();

        System.out.println("[Test] OVChipkaartDAO.findAll() geeft de volgende ovchipkaarten:");
        for (OVChipkaart r : ov) {
            System.out.println(r);
        }


        System.out.println();

        System.out.println("[Test] OVChipkaartDAO.findByReiziger() geeft het volgende: ");
        for (OVChipkaart r :odao.findByReiziger(r1) ) {
            System.out.println(r);
        }

        System.out.println();

        System.out.println("[Test] Eerst " + ov.size() + " chipkaarten, na OVChipkaart.save() ");
        odao.save(o);
        ov = odao.findall();
        System.out.println(ov.size() + " chipkaarten\n");

        System.out.println();

        System.out.println("[Test] Eerst " + ov.size() + " chipkaarten, na OVChipkaart.delete() ");
        odao.delete(o);
        ov = odao.findall();
        System.out.println(ov.size() + " chipkaarten\n");

        System.out.println();

        OVChipkaart oi = new OVChipkaart(43222, java.sql.Date.valueOf("2222-12-21"), 3, 200, r1 );
        odao.save(oi);
        r1.addOV(oi);


        System.out.println("[Test] Eerst heeft een reiziger de volgende OVChipkaarten: " );
        for (OVChipkaart ow : odao.findByReiziger(r1)) {
            System.out.println(ow);
        }

        System.out.println();

        System.out.println("na de functie odoa.update() heeft de reiziger de volgende OVChipkaarten:");

        OVChipkaart oiw = new OVChipkaart(43222, java.sql.Date.valueOf("2222-12-21"), 1, 12, r1 );
        odao.update(oiw);
        for (OVChipkaart ow : odao.findByReiziger(r1)) {
            System.out.println(ow);
        }

        odao.delete(oiw);



        Main.closeConnection();

    }

    public static void testProductDAO(ProductDAO pdao) throws SQLException {
        Product p = new Product(23, "Crying", "Allowed to cry for 5 min", 100);
        Product p1 = new Product(25, "Screeching", "Allowed to screech for 5 min", 400);
        Product p2 = new Product(23, "yo", "hee", 3);

        Reiziger r1 = new Reiziger(5, "M", null, "K", java.sql.Date.valueOf("2001-12-21"));
        OVChipkaart o = new OVChipkaart(333, java.sql.Date.valueOf("2001-12-21"), 1, 40, r1 );
        OVChipkaart o1 = new OVChipkaart(3456, java.sql.Date.valueOf("2001-12-21"), 2, 40, r1 );

        pdao.save(o, p, "active", java.sql.Date.valueOf("2020-08-07"));

        System.out.println("[Test] pdao.findAll() geeft het volgende: ");
        for (Product ppp : pdao.findAll()) {
            System.out.println(ppp);
        }

        System.out.println();

        System.out.println("[Test] pdao.findByOVChipkaart() geeft het volgende: ");
        System.out.println(pdao.findByOVChipkaart(o));

        System.out.println();
        int lengte = pdao.findAll().size();

        System.out.println("[Test] Eerst " + lengte + " aantal producten, na de functie pdao.save() ");
        pdao.save(o, p1, "yee", java.sql.Date.valueOf("2004-04-04"));
        lengte = pdao.findAll().size();
        System.out.println(lengte +  " aantal producten");

        System.out.println("[Test] Eerst " + lengte + " aantal producten, na de functie pdao.delete() ");
        pdao.delete(p1);
        lengte = pdao.findAll().size();
        System.out.println(lengte +  " aantal producten");

        System.out.println();

        System.out.println("[Test] Eerst ziet het product er zo uit: ");
        System.out.println(pdao.findByOVChipkaart(o));
        System.out.println(" na de pdao.update() functie ziet het product er zo uit: ");
        pdao.update(o, p2, "active", java.sql.Date.valueOf("2001-12-21"));
        System.out.println(pdao.findByOVChipkaart(o));

        pdao.delete(p);


    }
}

