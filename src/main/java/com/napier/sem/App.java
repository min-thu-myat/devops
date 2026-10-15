package com.napier.sem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class App {
    // Update credentials/URL to match your local database settings
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/employees?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "example";

    public static void main(String[] args) {
        App app = new App();

        // Test feature: Get Department by Name ("Sales")
        Department dept = app.getDepartment("Sales");

        if (dept != null) {
            System.out.println("Department: " + dept.getDept_name() + " (" + dept.getDept_no() + ")");
            if (dept.getManager() != null) {
                System.out.println("Manager: " + dept.getManager().getFirst_name() + " " + dept.getManager().getLast_name());
            }
            System.out.println("--- Employee Salaries ---");

            // Get and print salaries by department
            ArrayList<Employee> employees = app.getSalariesByDepartment(dept);
            for (Employee emp : employees) {
                System.out.println(emp.getEmp_no() + "\t" + emp.getFirst_name() + "\t" + emp.getLast_name() + "\t" + emp.getSalary());
            }
        } else {
            System.out.println("Department not found.");
        }
    }

    /**
     * Gets a department by name and fetches its manager (Exercise 1).
     */
    public Department getDepartment(String dept_name) {
        Department department = null;
        String sql = "SELECT d.dept_no, d.dept_name, m.emp_no FROM departments d " +
                "LEFT JOIN dept_manager m ON d.dept_no = m.dept_no AND m.to_date = '9999-01-01' " +
                "WHERE d.dept_name = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

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
        ArrayList<Employee> employees = new ArrayList<>();
        String sql = "SELECT employees.emp_no, employees.first_name, employees.last_name, salaries.salary " +
                "FROM employees, salaries, dept_emp, departments " +
                "WHERE employees.emp_no = salaries.emp_no " +
                "AND employees.emp_no = dept_emp.emp_no " +
                "AND dept_emp.dept_no = departments.dept_no " +
                "AND salaries.to_date = '9999-01-01' " +
                "AND departments.dept_no = ? " +
                "ORDER BY employees.emp_no ASC";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

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
     * Retrieves an employee by employee number, including their department info (Exercise 2).
     */
    public Employee getEmployee(int emp_no) {
        Employee employee = null;
        String sql = "SELECT e.emp_no, e.first_name, e.last_name, d.dept_no, d.dept_name " +
                "FROM employees e " +
                "JOIN dept_emp de ON e.emp_no = de.emp_no AND de.to_date = '9999-01-01' " +
                "JOIN departments d ON de.dept_no = d.dept_no " +
                "WHERE e.emp_no = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

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
     * Retrieves an employee by their first and last name (Exercise 3).
     */
    public Employee getEmployee(String firstName, String lastName) {
        Employee employee = null;
        String sql = "SELECT emp_no, first_name, last_name FROM employees WHERE first_name = ? AND last_name = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

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

    public void printSalaries(ArrayList<Employee> employees)
    {
        // Check employees is not null
        if (employees == null)
        {
            System.out.println("No employees");
            return;
        }
        // Print header
        System.out.println(String.format("%-10s %-15s %-20s %-8s", "Emp No", "First Name", "Last Name", "Salary"));
        // Loop over all employees in the list
        for (Employee emp : employees)
        {
            if (emp == null)
                continue;
            String emp_string =
                    String.format("%-10s %-15s %-20s %-8s",
                            emp.getEmp_no(), emp.getFirst_name(), emp.getLast_name(), emp.getSalary());
            System.out.println(emp_string);
        }
    }

    public void displayEmployee(Employee emp)
    {
        // Check if the employee object is null to prevent crashes
        if (emp == null)
        {
            System.out.println("No employee details provided.");
            return;
        }

        // Print employee details safely using getters
        System.out.println(String.format("Emp No: %-10d | Name: %-15s %-20s | Salary: %-8.2f",
                emp.getEmp_no(),
                emp.getFirst_name(),
                emp.getLast_name(),
                emp.getSalary()
        ));
    }

}