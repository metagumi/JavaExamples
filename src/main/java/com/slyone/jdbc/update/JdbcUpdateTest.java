package com.slyone.jdbc.update;

import java.sql.*;

public class JdbcUpdateTest {
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
            Statement stmt = conn.createStatement();

            String strUpdate = "update president set death = '1826-07-04' " +
                    "where last_name = 'Jefferson' and first_name = 'Thomas'";
            System.out.println("The SQL query is: " + strUpdate);
            int countUpdated = stmt.executeUpdate(strUpdate);
            System.out.println(countUpdated + " records affected.");

            String strSelect = "select last_name, first_name, birth, death from president";
            System.out.println("The SQL query is: " + strSelect);
            ResultSet rslt = stmt.executeQuery(strSelect);
            String lastName, firstName, birth, death;
            System.out.println("\n\nPresident's List:");

            while (rslt.next()) {
                lastName = rslt.getString("last_name");
                firstName = rslt.getString("first_name");
                birth = rslt.getString("birth");
                death = rslt.getString("death");
                System.out.println(lastName + "\t\t" + firstName + "\t" + birth + "\t" + death);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
