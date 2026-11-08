# Use Case 7: Update Employee Details

## Goal in Context

As an HR advisor I want to update an employee's details so that employee's details are kept up-to-date.

## Scope

HR System

## Level

User Goal

## Preconditions

- The HR advisor is authorised to update employee information.
- The employee exists in the system.

## Success Condition

The employee's updated details are successfully stored.

## Failed Condition

The employee's details are not updated because the employee does not exist or the new information is invalid.

## Primary Actor

HR Advisor

## Trigger

The HR advisor chooses to update an employee's details.

## Main Success Scenario

1. The HR advisor searches for the employee.
2. The system displays the employee's existing details.
3. The HR advisor selects the information to update.
4. The HR advisor enters the new information.
5. The system validates the updated information.
6. The system saves the changes.
7. The system confirms that the employee's details have been updated.

## Extensions

- If the employee does not exist, the system displays an error.
- If invalid information is entered, the system asks the HR advisor to correct it.
- If the database update fails, the system displays an error.

## Sub-variations

The HR advisor may update one or several employee details.

## Schedule

To be implemented according to the current sprint schedule.