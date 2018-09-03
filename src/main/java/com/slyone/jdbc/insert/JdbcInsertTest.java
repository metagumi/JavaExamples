package com.slyone.jdbc.insert;

import java.sql.*;


public class JdbcInsertTest {
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

            String strDelete = "delete from president where last_name = 'Trump'";
            System.out.println("The SQL query is: " + strDelete);
            int countDeleted = stmt.executeUpdate(strDelete);
            System.out.println(countDeleted + " records deleted.");

            String sqlInsert = "insert into president(last_name,first_name,suffix,city,state,birth,death)" +
                    "values ('Obama','Barack',null,'Chicago','IL','9999-12-31','9999-12-31')";
            System.out.println("The SQL query is: " + sqlInsert);
            int countInserted = stmt.executeUpdate(sqlInsert);
            System.out.println(countInserted + " records inserted.\n");

            sqlInsert = "insert into president(last_name,first_name,suffix,city,state,birth,death) values " +
                    "('Trump','Donald',null,'Queens','NY','9999-12-31','9999-12-31')";
            System.out.println("The SQL query is: " + sqlInsert);
            countInserted = stmt.executeUpdate(sqlInsert);
            System.out.println(countInserted + " records inserted.\n");

            sqlInsert = "insert into president(last_name,first_name,suffix,city,state,birth,death) values " +
                    "('Trump','Donald',null,'Queens','NY','9999-12-31','9999-12-31')";
            System.out.println("The SQL query is: " + sqlInsert);
            countInserted = stmt.executeUpdate(sqlInsert);
            System.out.println(countInserted + " records inserted.\n");

            String lastName, firstName, birth, death;
            String strSelect = "select last_name,first_name,birth,death from president";
            System.out.println("The SQL query is: " + strSelect);
            ResultSet rslt = stmt.executeQuery(strSelect);
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