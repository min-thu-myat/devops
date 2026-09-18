package com.napier.sem;

import java.sql.*;

public class App
{
    /** Connection to MySQL database. */
    private Connection con = null;

    /** Connect to the MySQL database. */
    public void connect()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                Thread.sleep(30000);
                con = DriverManager.getConnection("jdbc:mysql://db:3306/employees?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /** Disconnect from the MySQL database. */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

    /** Get an employee's detailed record by ID. */
    public Employee getEmployee(int ID)
    {
        try
        {
            Statement stmt = con.createStatement();
            String strSelect =
                    "SELECT emp.emp_no, emp.first_name, emp.last_name, "
                            + "t.title, s.salary, d.dept_name, "
                            + "CONCAT(mgr.first_name, ' ', mgr.last_name) AS manager "
                            + "FROM employees emp "
                            + "JOIN titles t ON emp.emp_no = t.emp_no AND t.to_date = '9999-01-01' "
                            + "JOIN salaries s ON emp.emp_no = s.emp_no AND s.to_date = '9999-01-01' "
                            + "JOIN dept_emp de ON emp.emp_no = de.emp_no AND de.to_date = '9999-01-01' "
                            + "JOIN departments d ON de.dept_no = d.dept_no "
                            + "JOIN dept_manager dm ON d.dept_no = dm.dept_no AND dm.to_date = '9999-01-01' "
                            + "JOIN employees mgr ON dm.emp_no = mgr.emp_no "
                            + "WHERE emp.emp_no = " + ID;

            ResultSet rset = stmt.executeQuery(strSelect);

            if (rset.next())
            {
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("emp_no");
                emp.first_name = rset.getString("first_name");
                emp.last_name = rset.getString("last_name");
                emp.title = rset.getString("title");
                emp.salary = rset.getInt("salary");
                emp.dept_name = rset.getString("dept_name");
                emp.manager = rset.getString("manager");
                return emp;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    /** Display employee details to console. */
    public void displayEmployee(Employee emp)
    {
        if (emp != null)
        {
            System.out.println(
                    emp.emp_no + " "
                            + emp.first_name + " "
                            + emp.last_name + "\n"
                            + emp.title + "\n"
                            + "Salary:" + emp.salary + "\n"
                            + emp.dept_name + "\n"
                            + "Manager: " + emp.manager + "\n");
        }
    }

    public static void main(String[] args)
    {
        App a = new App();
        a.connect();

        Employee emp = a.getEmployee(255530);
        a.displayEmployee(emp);

        a.disconnect();
    }
}