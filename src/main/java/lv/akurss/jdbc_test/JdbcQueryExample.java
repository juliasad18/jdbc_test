package lv.akurss.jdbc_test;

import java.sql.*;

public class JdbcQueryExample {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Experimental";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) throws Exception {

        Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD); //statichnij metod!
        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("select * from users"); //ResultSet - vozvrawaet otvet na moj zapros, NO EWE NE OTOBRAZAET V KONSOLE!!!!

        while (rs.next()) { //beskonecnij cikl kotorij govorit o perehode na sledujushuju stroku. bez nego budet schitatj toljko pervuju
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            System.out.println("ID:" + id + " NAME: " + name + " SURNAME: " + surname);
        }

        PreparedStatement pstmt = conn.prepareStatement("select * from users where name = ? and surname = ?");
        pstmt.setString(1, "Johny");
        pstmt.setString(2, "Smitty");

        ResultSet rs2 = pstmt.executeQuery();
        while (rs2.next()) { //beskonecnij cikl kotorij govorit o perehode na sledujushuju stroku. bez nego budet schitatj toljko pervuju
            int id = rs2.getInt("id");
            String name = rs2.getString("name");
            String surname = rs2.getString("surname");
            System.out.println("ID:" + id + " NAME: " + name + " SURNAME: " + surname);
        }

        stmt.close();
        pstmt.close();
        conn.close();


    }
}