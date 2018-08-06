package com.slyone.jdbc;

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
            manipulateData(args);
            conn.commit();
            // Cleanup
            stmt.close();
            conn.close();
        } catch( Exception e) {
            e.printStackTrace();
        }
    }

    public static void checkArguments(String args[]) {
        if (args.length != 3) {
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

    public static void manipulateData (String args[])
            throws SQLException   {

    }
}
