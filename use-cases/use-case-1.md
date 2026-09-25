# Use Case 1: Get Salaries for All Employees

## Goal in Context

As an HR advisor I want to produce a report on the salary of all employees so that I can support financial reporting of the organisation.

## Scope

HR System

## Level

User Goal

## Preconditions

- The HR advisor is authorised to access salary information.
- Employee and salary information is available in the system.

## Success Condition

A salary report containing all current employees and their salaries is produced successfully.

## Failed Condition

The system cannot produce the report because the required employee or salary information is unavailable.

## Primary Actor

HR Advisor

## Trigger

The HR advisor requests a report containing the salaries of all employees.

## Main Success Scenario

1. The HR advisor requests the salary report.
2. The HR System retrieves employee information.
3. The HR System retrieves current salary information.
4. The system matches employees with their current salaries.
5. The system generates the salary report.
6. The system displays the report to the HR advisor.

## Extensions

- If employee information cannot be retrieved, the system displays an error message.
- If salary information cannot be retrieved, the system displays an error message.
- If no employee records are available, the system informs the HR advisor that no data is available.

## Sub-variations

The HR advisor may choose to display or print the generated report.

## Schedule

To be implemented according to the current sprint schedule.