package com.napier.devops;

import com.napier.sem.App;
import com.napier.sem.Employee;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060", 30000);
    }

    @Test
    void testGetEmployee()
    {
        Employee emp = app.getEmployee(10001);

        assertNotNull(emp, "Employee should not be null from the database");

        assertEquals(emp.getEmp_no(), 10001);
        assertEquals(emp.getFirst_name(), "Georgi");
        assertEquals(emp.getLast_name(), "Facello");
    }
}