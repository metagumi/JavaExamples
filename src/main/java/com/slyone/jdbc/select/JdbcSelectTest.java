package com.slyone.jdbc.select;

import java.sql.*;

public class JdbcSelectTest {
    public static void main(String[] args) {

        try {
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);

            String serverName = "192.168.1.201";
            String myDatabase = "sampdb";
            String url = "jdbc:mysql://" + serverName + "/" + myDatabase;

            String dbuser = "scharvey";
            String password = "kSQgx2Mc";

            Connection conn = DriverManager.getConnection(url, dbuser, password);
            System.out.println("Database connection made");

            String query = "select last_name, first_name, birth from president";

            Statement stmt = conn.createStatement();
            ResultSet rslt = stmt.executeQuery(query);

            String lastName, firstName, birth;

            System.out.println("\n\nPresident's List:");

            while (rslt.next()) {
                lastName = rslt.getString("last_name");
                firstName = rslt.getString("first_name");
                birth = rslt.getString("birth");
                System.out.println(lastName + "\t\t" + firstName + "\t" + birth);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



//junk

