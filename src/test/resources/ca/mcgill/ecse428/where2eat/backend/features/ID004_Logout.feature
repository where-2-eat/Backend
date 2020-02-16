Feature: Logout of account
  As a user currently logged in, I want to logout of my account

  Background:
    Given The user is logged in

  Scenario: Successful logout
    When The user logs out
    Then The login page should be displayed
    And No user should be logged in