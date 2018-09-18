package com.slyone.jdbc.prepared;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeReport {
    public static void main(String[] args) {
        //String driverName = "com.mysql.jdbc.Driver";
        //Class.forName(driverName);

        String serverName = "192.168.1.201";
        String myDatabase = "sampdb";
        String url = "jdbc:mysql://" + serverName + "/" + myDatabase;

        String dbuser = "scharvey";
        String password = "kSQgx2Mc";

        String schemaName = "sampdb";
        String srchName = "'Peac'";

        String sqlSelect = "select emp.first_name, emp.last_name, emp.hire_date, dept.dept_no " +
                "from #schemaName#.employees emp join #schemaName#.dept_emp dept " +
                "on emp.emp_no = dept.emp_no where emp.last_name = #lname#";
        String finalSqlSelect = sqlSelect.toString().replace("#schemaName#",schemaName)
                                                     .replace("#lname#",srchName);

        System.out.println(finalSqlSelect);

        try {
            Connection conn = DriverManager.getConnection(url, dbuser, password);
            PreparedStatement pst = conn.prepareStatement(finalSqlSelect);
            ResultSet rslt = pst.executeQuery(finalSqlSelect);

            String lastName, firstName, hireDate, empDept;

            List<String> list = new ArrayList<>();

            while (rslt.next()) {
                lastName = rslt.getString("last_name");
                firstName = rslt.getString("first_name");
                hireDate = rslt.getString("hire_date");
                empDept = rslt.getString("dept_no");
                System.out.println(lastName + "\t\t" + firstName + "\t" + hireDate + "\t" + empDept);
                list.add(lastName+firstName+hireDate+empDept);

                System.out.println(list);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
