package com.slyone.jdbc.select;

import java.sql.*;

public class AddPresident {
    static Connection conn;
    static Statement stmt;

    public static void main(String[] args) {
        try {
            checkArguments(args);
            configureConnection();
            configureTransactions();
            stmt = conn.createStatement();
            // Begin transaction
            conn.setAutoCommit(false);
            //args[0] = "Obama";
            manipulateData(args);
            conn.commit();
            // Cleanup
            stmt.close();
            conn.close();
        } catch( Exception e) {
            e.printStackTrace();
        }
    }

    public static void checkArguments(String [] args) {
        if (args.length <= 6) {
            String msg = "USAGE: java AddPresident " +
                    "<lastname> <firstname> <suffix> <city> <state> <birth> <death>";
            throw new IllegalArgumentException(msg);
        }
    }

    public static void configureConnection() throws
            ClassNotFoundException, SQLException {
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
    }

    public static void configureTransactions() throws SQLException {
        DatabaseMetaData dbmd = conn.getMetaData();
        if (!dbmd.supportsTransactions()) {
            String msg = "No Transaction Support";
            throw new RuntimeException(msg);
        }

        int txLevel = conn.getTransactionIsolation();
        // try to set a higher TRANSACTION LEVEL
        if (txLevel == Connection.TRANSACTION_NONE) {
            int txRC = Connection.TRANSACTION_READ_COMMITTED;
            boolean readCommitted = dbmd.supportsTransactionIsolationLevel(txRC);
            if (readCommitted)
                conn.setTransactionIsolation(txRC);
        }
    }

    public static void manipulateData (String [] args)
            throws SQLException   {
        String sql;
        ResultSet rslt = null;

        try {
            // get new President
            sql = "SELECT MAX(id) AS " + "Max_id FROM president";
            rslt = stmt.executeQuery(sql);
           int authID = 0;
           String dummy = " ";
           while (rslt.next()) {
               authID = rslt.getInt("Max_id")+ 1;
           }
           System.out.println("next id " + authID);
            // add president
            String last_neme = args[0];
            String first_name = args[1];
            String suffix = args[2];
            String city = args[3];
            String state = args[4];
            String birth = args[5];
            String death = args[6];
            sql = "INSERT INTO president (last_name, first_name, suffix, city, state, birth, death) values("+
                    "'last_name'"+","+"'first_name'"+","+"'null'"+","+"'city'"+","+"'null'"+","+"'birth'"+","+"'death'"+")";
            //stmt.execute(sql);
            System.out.println(sql);
            conn.rollback();
        } catch (SQLException e) {
            conn.rollback();
            throw new SQLException(e.getMessage());
        }
        // modify code to add data to table  see new table layout
        //String query = "select last_name, first_name, birth from president";

        //Statement stmt = conn.createStatement();
        //rslt = stmt.executeQuery(query);

        //String lastName, firstName, birth;

        //while (rslt.next()) {
        //    lastName = rslt.getString("last_name");
        //    firstName = rslt.getString("first_name");
        //    birth = rslt.getString("birth");
        //    System.out.println(lastName + "\t\t" + firstName + "\t" + birth);
        //}

        //conn.close();

    }
}
