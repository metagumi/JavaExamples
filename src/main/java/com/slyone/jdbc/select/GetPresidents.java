package com.slyone.jdbc.select;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GetPresidents {
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
            conn.setAutoCommit(false);
            System.out.println("Database connection made");

            String query = "select last_name, first_name, birth from president";

            Statement stmt = conn.createStatement();
            ResultSet rslt = stmt.executeQuery(query);

            String lastName, firstName, birth;

            System.out.println("\n\nPresident's List:");

            List<String> list = new ArrayList<>();

            while (rslt.next()) {
                lastName = rslt.getString("last_name");
                firstName = rslt.getString("first_name");
                birth = rslt.getString("birth");
                System.out.println(lastName + "\t\t" + firstName + "\t" + birth);
                list.add(lastName+firstName+birth);

                System.out.println(list);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
