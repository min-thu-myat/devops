# Use Case 8: Delete Employee Details

## Goal in Context

As an HR advisor I want to delete an employee's details so that the company is compliant with data retention legislation.

## Scope

HR System

## Level

User Goal

## Preconditions

- The HR advisor is authorised to delete employee information.
- The employee exists in the system.

## Success Condition

The employee's details are successfully deleted from the HR System.

## Failed Condition

The employee's details are not deleted because the employee does not exist or the deletion cannot be completed.

## Primary Actor

HR Advisor

## Trigger

The HR advisor chooses to delete an employee's details.

## Main Success Scenario

1. The HR advisor searches for the employee.
2. The system displays the employee's details.
3. The HR advisor selects the delete option.
4. The system asks for confirmation.
5. The HR advisor confirms the deletion.
6. The system deletes the employee's details.
7. The system confirms that the employee's details have been deleted.

## Extensions

- If the employee does not exist, the system displays an error.
- If the HR advisor cancels the deletion, no information is deleted.
- If the deletion fails, the system displays an error message.

## Sub-variations

The HR advisor can cancel the deletion before confirmation.

## Schedule

To be implemented according to the current sprint schedule.