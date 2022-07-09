package my;

import java.sql.*;
import java.util.Calendar;
import java.util.TimeZone;

public class Main {

    public static void main(String[] args) throws SQLException, InterruptedException {
        Connection conn = DriverManager.getConnection("jdbc:duckdb:");
        Statement stmt = conn.createStatement();
        ResultSet rs;

        stmt.executeUpdate("CREATE TABLE t1(ts timestamp without time zone);");
        stmt.executeUpdate("INSERT INTO t1 VALUES (now());");

        rs = stmt.executeQuery("SELECT ts FROM t1;");
        while (rs.next()) {
            System.out.println(rs.getString(1));
            System.out.println(rs.getTimestamp(1));
        }
        rs.close();

        stmt.executeUpdate("CREATE TABLE t2(ts timestamp with time zone);");
        stmt.executeUpdate("INSERT INTO t2 VALUES (now());");

        rs = stmt.executeQuery("SELECT ts FROM t2;");
        while (rs.next()) {
            System.out.println(rs.getString(1));
            System.out.println(rs.getTimestamp(1));
        }
        rs.close();

        conn.close();
    }

}
