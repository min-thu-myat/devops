# Use Case 2: Get Salaries by Department

## Goal in Context

As an HR advisor I want to produce a report on the salary of employees in a department so that I can support financial reporting of the organisation.

## Scope

HR System

## Level

User Goal

## Preconditions

- The HR advisor is authorised to access salary information.
- Employee, department and salary information is available.
- The requested department exists.

## Success Condition

A salary report for employees belonging to the selected department is produced.

## Failed Condition

The system cannot produce the report because the department or required salary information is unavailable.

## Primary Actor

HR Advisor

## Trigger

The HR advisor requests salary information for a specific department.

## Main Success Scenario

1. The HR advisor requests a salary report.
2. The system asks for the department.
3. The HR advisor provides the department.
4. The system finds employees belonging to the department.
5. The system retrieves their current salaries.
6. The system generates the department salary report.
7. The system displays the report.

## Extensions

- If the department does not exist, the system displays an error message.
- If no employees belong to the department, the system informs the HR advisor.
- If salary information cannot be retrieved, the system displays an error message.

## Sub-variations

The HR advisor can select different departments to generate separate reports.

## Schedule

To be implemented according to the current sprint schedule.