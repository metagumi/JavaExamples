package com.slyone.jdbc.select;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class SimpleQuery {
    public static void main(String[] args) {

        //Properties prop = new Properties();
        //FileInputStream input = null;
        Connection conn = null;

        try {
            // Load the database driver
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);

            String serverName = "192.168.1.201";
            String myDatabase = "sampdb";
            String url = "jdbc:mysql://" + serverName + "/" + myDatabase;

            String dbuser = "scharvey";
            String password = "kSQgx2Mc";

            conn = DriverManager.getConnection(url, dbuser, password);
            System.out.println("Database connection made");

            String query = "select last_name, first_name, birth from president";

            Statement stmt = conn.createStatement();
            ResultSet rslt = stmt.executeQuery(query);

            String lastName, firstName, birth;

            while (rslt.next()) {
                lastName = rslt.getString("last_name");
                firstName = rslt.getString("first_name");
                birth = rslt.getString("birth");
                System.out.println(lastName + "\t\t" + firstName + "\t" + birth);
            }

            conn.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
