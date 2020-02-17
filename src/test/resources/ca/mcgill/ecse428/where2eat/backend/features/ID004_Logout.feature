Feature: Logout of account
  As a user currently logged in, I want to logout of my account

  Background:
    Given The following user is logged in:
      |full_name    |_username     |_password    |
      |Steve Jobs   |steve99       |iphoneX89    |

  Scenario: Successful logout
    When The user logs out
    Then The blacklist should contain the user