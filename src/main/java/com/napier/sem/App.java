package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class App {

    // Shared connection used by all methods
    private Connection con = null;

    public static void main(String[] args) {
        App a = new App();

        // Connect (local by default, or use command-line args)
        if (args.length < 1) {
            a.connect("localhost:33060", 10000);   // local Docker port
        } else {
            a.connect(args[0], Integer.parseInt(args[1]));
        }

        // Example usage
        Department dept = a.getDepartment("Development");

        if (dept != null) {
            System.out.println("Department: " + dept.getDept_name() + " (" + dept.getDept_no() + ")");
            if (dept.getManager() != null) {
                System.out.println("Manager: " + dept.getManager().getFirst_name() + " "
                        + dept.getManager().getLast_name());
            }
            System.out.println("--- Employee Salaries ---");

            ArrayList<Employee> employees = a.getSalariesByDepartment(dept);
            a.printSalaries(employees);
        } else {
            System.out.println("Department not found.");
        }

        a.disconnect();
    }

    /**
     * Connect to the MySQL database.
     * @param location  hostname:port (e.g. "localhost:33060" or "db:3306")
     * @param delay     wait time between retries (milliseconds)
     */
    public void connect(String location, int delay) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        boolean shouldWait = false;

        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                if (shouldWait) {
                    Thread.sleep(delay);
                }

                con = DriverManager.getConnection(
                        "jdbc:mysql://" + location + "/employees?allowPublicKeyRetrieval=true&useSSL=false",
                        "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());
                shouldWait = true;
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Successfully disconnected");
            } catch (SQLException e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    /**
     * Gets a department by name and fetches its manager.
     */
    public Department getDepartment(String dept_name) {
        if (con == null) return null;

        Department department = null;
        String sql = "SELECT d.dept_no, d.dept_name, m.emp_no FROM departments d " +
                "LEFT JOIN dept_manager m ON d.dept_no = m.dept_no AND m.to_date = '9999-01-01' " +
                "WHERE d.dept_name = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, dept_name);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String deptNo = rs.getString("dept_no");
                String deptName = rs.getString("dept_name");
                int managerEmpNo = rs.getInt("emp_no");

                Employee manager = null;
                if (managerEmpNo > 0) {
                    manager = getEmployee(managerEmpNo);
                }

                department = new Department(deptNo, deptName, manager);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;
    }

    /**
     * Retrieves all current employee salaries for a given department.
     */
    public ArrayList<Employee> getSalariesByDepartment(Department dept) {
        if (con == null || dept == null) return new ArrayList<>();

        ArrayList<Employee> employees = new ArrayList<>();
        String sql = "SELECT employees.emp_no, employees.first_name, employees.last_name, salaries.salary " +
                "FROM employees, salaries, dept_emp, departments " +
                "WHERE employees.emp_no = salaries.emp_no " +
                "AND employees.emp_no = dept_emp.emp_no " +
                "AND dept_emp.dept_no = departments.dept_no " +
                "AND salaries.to_date = '9999-01-01' " +
                "AND departments.dept_no = ? " +
                "ORDER BY employees.emp_no ASC";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, dept.getDept_no());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int empNo = rs.getInt("emp_no");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                double salary = rs.getDouble("salary");

                Employee emp = new Employee(empNo, firstName, lastName, salary);
                employees.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    /**
     * Retrieves an employee by employee number.
     */
    public Employee getEmployee(int emp_no) {
        if (con == null) return null;

        Employee employee = null;
        String sql = "SELECT e.emp_no, e.first_name, e.last_name, d.dept_no, d.dept_name " +
                "FROM employees e " +
                "JOIN dept_emp de ON e.emp_no = de.emp_no AND de.to_date = '9999-01-01' " +
                "JOIN departments d ON de.dept_no = d.dept_no " +
                "WHERE e.emp_no = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, emp_no);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Department dept = new Department(rs.getString("dept_no"), rs.getString("dept_name"), null);
                employee = new Employee(rs.getInt("emp_no"), rs.getString("first_name"), rs.getString("last_name"));
                employee.setDept(dept);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    /**
     * Retrieves an employee by first and last name.
     */
    public Employee getEmployee(String firstName, String lastName) {
        if (con == null) return null;

        Employee employee = null;
        String sql = "SELECT emp_no, first_name, last_name FROM employees WHERE first_name = ? AND last_name = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                employee = new Employee(rs.getInt("emp_no"), rs.getString("first_name"), rs.getString("last_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public void printSalaries(ArrayList<Employee> employees) {
        if (employees == null) {
            System.out.println("No employees");
            return;
        }
        System.out.println(String.format("%-10s %-15s %-20s %-8s", "Emp No", "First Name", "Last Name", "Salary"));
        for (Employee emp : employees) {
            if (emp == null) continue;
            String emp_string = String.format("%-10s %-15s %-20s %-8s",
                    emp.getEmp_no(), emp.getFirst_name(), emp.getLast_name(), emp.getSalary());
            System.out.println(emp_string);
        }
    }

    public void displayEmployee(Employee emp) {
        if (emp == null) {
            System.out.println("No employee details provided.");
            return;
        }
        System.out.println(String.format("Emp No: %-10d | Name: %-15s %-20s | Salary: %-8.2f",
                emp.getEmp_no(),
                emp.getFirst_name(),
                emp.getLast_name(),
                emp.getSalary()
        ));
    }
}