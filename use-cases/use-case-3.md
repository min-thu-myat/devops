# Use Case 3: Get Salaries for My Department

## Goal in Context

As a department manager I want to produce a report on the salary of employees in my department so that I can support financial reporting for my department.

## Scope

HR System

## Level

User Goal

## Preconditions

- The department manager is authorised to access salary information.
- The manager is associated with a department.
- Employee and salary information is available.

## Success Condition

A salary report containing employees from the manager's department is produced.

## Failed Condition

The system cannot produce the report because the manager's department or salary information is unavailable.

## Primary Actor

Department Manager

## Trigger

The department manager requests a salary report for their department.

## Main Success Scenario

1. The department manager requests the salary report.
2. The system identifies the manager's department.
3. The system retrieves employees from that department.
4. The system retrieves their current salary information.
5. The system generates the salary report.
6. The system displays the report to the manager.

## Extensions

- If the manager is not associated with a department, the system displays an error.
- If no employees are found, the system informs the manager.
- If salary information cannot be retrieved, the system displays an error.

## Sub-variations

The report may contain all employees currently assigned to the manager's department.

## Schedule

To be implemented according to the current sprint schedule.