Feature: Doctrin Title List test.

  Background: 
    Given The Web browser "FF" with version "75" is Opened
	#To toggle browsers enter FF or Chrome with version number, in the above Given.
	
  Scenario: To test Login with nonexistent user
    Given Navigate to "career" Page
    When Select all the names of people in that page along with their titles
    Then Write to a file the list of names of people along with their titles