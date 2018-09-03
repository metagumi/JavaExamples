package com.slyone.jdbc.transactions;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
    /**
     * Insert and assign skills to a specific candidates
     * @param firstName
     * @param lastName
     * @param dob
     * @param email
     * @param phone
     * @param skills
     */


    public static void addCandidate(String firstName, String lastName, Date dob, String email, String phone, int[] skills) {

        Connection conn = null;
        // for insert a new candidate
        PreparedStatement pstmt = null;
        // for assign skills to candidate
        PreparedStatement pstmtAssignment = null;
        // for getting candidate id
        ResultSet rs = null;

        try {
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);

            String serverName = "192.168.1.201";
            String myDatabase = "sampdb";
            String url = "jdbc:mysql://" + serverName + "/" + myDatabase;

            String dbuser = "scharvey";
            String password = "kSQgx2Mc";

            conn = DriverManager.getConnection(url, dbuser, password);
            System.out.println("Database connection made");
            //conn = MySQLJDBCUtil.getConnection();
            // set auto commit to false
            conn.setAutoCommit(false);
            //
            // Insert candidate
            //
            String sqlInsert = "INSERT INTO candidates(first_name,last_name,dob,phone,email) "
                    + "VALUES(?,?,?,?,?)";

            pstmt = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setDate(3, dob);
            pstmt.setString(4, phone);
            pstmt.setString(5, email);

            int rowAffected = pstmt.executeUpdate();

            // get candidate id
            rs = pstmt.getGeneratedKeys();
            int candidateId = 0;
            if (rs.next())
                candidateId = rs.getInt(1);
            //
            // in case the insert operation successes, assign skills to candidate
            //
            if (rowAffected == 1) {
                // assign skills to candidates
                String sqlPivot = "INSERT INTO candidate_skills(candidate_id,skill_id) "
                        + "VALUES(?,?)";

                pstmtAssignment = conn.prepareStatement(sqlPivot);
                for (int skillId : skills) {

                    pstmtAssignment.setInt(1, candidateId);
                    pstmtAssignment.setInt(2, skillId);

                    pstmtAssignment.executeUpdate();
                }
                conn.commit();
            } else {
                conn.rollback();
            }
        } catch (SQLException ex) {
            // roll back the transaction
            try {
                if (conn != null)
                    conn.rollback();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (pstmtAssignment != null) pstmtAssignment.close();
                if (conn != null) conn.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        // insert and assign skills
        int[] skills = {1, 2, 3};
        addCandidate("John", "Doe", Date.valueOf("1990-01-04"),
                "john.d@yahoo.com", "(408) 898-5641", skills);
    }
}
