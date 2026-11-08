# Use Case 5: Add New Employee

## Goal in Context

As an HR advisor I want to add a new employee's details so that I can ensure the new employee is paid.

## Scope

HR System

## Level

User Goal

## Preconditions

- The HR advisor is authorised to add employees.
- The HR System is available.

## Success Condition

The new employee's details are successfully stored in the HR System.

## Failed Condition

The employee cannot be added because required information is missing or invalid.

## Primary Actor

HR Advisor

## Trigger

The HR advisor chooses to add a new employee.

## Main Success Scenario

1. The HR advisor selects the option to add a new employee.
2. The system displays the employee details form.
3. The HR advisor enters the employee's details.
4. The system validates the entered information.
5. The system stores the new employee's details.
6. The system confirms that the employee has been added.

## Extensions

- If required information is missing, the system asks the HR advisor to provide it.
- If the employee identifier already exists, the system displays an error.
- If invalid information is entered, the system asks the HR advisor to correct it.
- If the database cannot be updated, the system displays an error.

## Sub-variations

The HR advisor may cancel the operation before saving the employee.

## Schedule

To be implemented according to the current sprint schedule.