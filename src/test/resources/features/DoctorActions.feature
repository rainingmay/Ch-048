Feature: Using actions of particular doctor
  As a manager I can interact with particular doctor from list

  Background:
    Given the manager is on dashboard of particular hospital in order to interact with doctor

  Scenario: Showing doctor details form
    When Manager click on the details button of particular doctor
    Then Pop up form with detaild information about particular doctor

  Scenario: Showing doctor edit form
    When Manager click on the edit button of particular doctor
    Then Pop up form with information to edit about particular doctor

  Scenario: Going to the scheduler page
    When Manager click on the scheduler button of particular doctor
    Then Moving to the scheduler page of particular doctor

  Scenario: Deleting of doctor
    When Manager click on delete button of particular doctor
    Then Pop up form with confirmation for deleting

  Scenario: Checking whether proper popup is calling
    When Manager click on the details button of particular doctor
    Then Name of doctor in details form should match with name from table

#  Scenario: Checking edition ability
#    When Manager click on the edit button of particular doctor
#    And Manager type diffrent first name, second name, education and addres
#    And click on save button

#  Scenario: Checing deliting ability
#    When Manager click on delete button of particular doctor
#    And Confirm delition
#    Then Doctor shouldn't apear in the table