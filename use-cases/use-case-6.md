# Use Case 6: View Employee Details

## Goal in Context

As an HR advisor I want to view an employee's details so that the employee's promotion request can be supported.

## Scope

HR System

## Level

User Goal

## Preconditions

- The HR advisor is authorised to view employee information.
- The employee exists in the system.

## Success Condition

The requested employee's details are displayed.

## Failed Condition

The employee's details cannot be displayed because the employee does not exist or the information cannot be retrieved.

## Primary Actor

HR Advisor

## Trigger

The HR advisor searches for an employee.

## Main Success Scenario

1. The HR advisor requests to view an employee's details.
2. The system asks for the employee identifier.
3. The HR advisor provides the identifier.
4. The system searches for the employee.
5. The system retrieves the employee's details.
6. The system displays the employee's details.

## Extensions

- If the employee does not exist, the system displays an error message.
- If the employee identifier is invalid, the system asks for a valid identifier.
- If employee information cannot be retrieved, the system displays an error.

## Sub-variations

The HR advisor may search using the employee identifier or another supported employee search method.

## Schedule

To be implemented according to the current sprint schedule.