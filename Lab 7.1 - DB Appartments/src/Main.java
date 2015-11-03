import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yegor2 on 11/3/2015.
 */
public class Main {

    static class Appartment{
        public String address;
        public int area;
        public int roomCount;
        public double price;
        public int distrinctId;

        public Appartment(String address, int area, int roomCount, double price, int distrinctId){
            this.address = address;
            this.area = area;
            this.roomCount = roomCount;
            this.price = price;
            this.distrinctId = distrinctId;

        }

    }

    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/YegorDb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    private static final String CREATE_TABLE_APPARTMENTS_SQL = "CREATE TABLE APPARTMENTS(\n" +
            "AppartmentID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
            "Address VARCHAR(200) NULL,\n" +
            "Area INT DEFAULT NULL,\n" +
            "RoomsCount INT DEFAULT NULL,\n" +
            "Price DOUBLE DEFAULT NULL,\n" +
            "DistrictID INT NOT NULL ,\n" +
            "FOREIGN KEY (DistrictID) REFERENCES DISTRICTS(DistrictID)\n" +
            ");";

    private static final String CREATE_TABLE_DISTRICTS_SQL = "CREATE TABLE DISTRICTS(\n" +
            "DistrictID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
            "Name varchar(100) DEFAULT NULL\n" +
            ");";


    private static Connection getDBConnection() {
        Connection dbConnection = null;


        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return dbConnection;
    }

    public static void main(String[] args) {

        Connection conn = getDBConnection();

        try {

            if (conn == null) {
                System.out.println("Error establishing connection");
                return;
            }

            Statement st = conn.createStatement();
            try {
                st.execute(CREATE_TABLE_DISTRICTS_SQL);
            } finally {
                if (st != null) st.close();
            }

            st = conn.createStatement();
            try {
                st.execute(CREATE_TABLE_APPARTMENTS_SQL);
            } finally {
                if (st != null) st.close();
            }


            PreparedStatement ps = null;
            String[] districts = {"Borshchagovka","Goloseevo","Darnitskyi","Dneprovskyi","Pecherskyi","Obolon","Nivki", "Shevchenkovsky","Teremki", "Troeshchina", "Podol"};

            try {

                for (String s: districts) {

                    ps = conn.prepareStatement("insert into DISTRICTS(Name) values (?)");
                    ps.setString(1, s);
                    ps.executeUpdate();
                }
            } finally {
                if (ps != null) ps.close();
            }


            List<Appartment> appartmentList = new ArrayList<Appartment>();
            appartmentList.add(new Appartment("28 Khreshatik str",100,4,1000000,5));
            appartmentList.add(new Appartment("12 Grushevskyi str",90,4,500000,5));
            appartmentList.add(new Appartment("23 Kharkivske shosse str",90,3,40000,3));
            appartmentList.add(new Appartment("16 Grigorenka str",80,2,80000,3));
            appartmentList.add(new Appartment("34 Grigorenka str",75,2,70000,3));
            appartmentList.add(new Appartment("56a Lepse blvd",75,2,50000,1));


            try {

                for (Appartment a: appartmentList) {

                    ps = conn.prepareStatement("insert into APPARTMENTS(Address,Area,RoomsCount,Price,DistrictID) values(?,?,?,?,?)");
                    ps.setString(1, a.address);
                    ps.setInt(2, a.area);
                    ps.setInt(3, a.roomCount);
                    ps.setDouble(4, a.price);
                    ps.setInt(5, a.distrinctId);
                    ps.executeUpdate();
                }
            } finally {
                if (ps != null) ps.close();
            }



            st = conn.createStatement();
            String address = null;
            double price = 0;
            System.out.println("List of appartments in Darnitskyi district:");

            try {
                ResultSet rs = st.executeQuery("SELECT address, price from appartments t1 INNER JOIN districts t2 on t1.districtId = t2.DistrictId WHERE t2.name = 'Darnitskyi'");
                while (rs.next()) {
                    address = rs.getString(1);
                    price = rs.getDouble(2);
                    System.out.println("found appartment: " + address + " price: " + String.valueOf(price));
                }
            } finally {
                if (st != null) st.close();
            }



        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {

            if (conn !=null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

        }






    }
}
