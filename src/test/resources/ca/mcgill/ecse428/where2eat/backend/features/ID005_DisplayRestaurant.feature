Feature: Display Restaurant
  As an existing user, I want to see the details of the chosen restaurant.

  Background:
    Given The following restaurants exist in our system
    | r_name    | r_address         | r_lng | r_lat | r_type    |
    | Boustan   | 123, rue chat     | 73.23 | 51.43 | lebanese  |
    | McDonalds | rue, st-catherine | 43.23 | 53.32 | burgers   |
    And The following users are already registered
    |full_name    |_username     |_password    |
    |Steve Jobs   |steve99       |iphoneX89    |
    |John Doe     |johnDoe02     |abcd123      |
    |Imad Dodin   |idodin        |abcd123      |
    And The following user group exists
    | admin   | member1   | member2 |
    | steve99 | johnDoe02 | idodin  |


  Scenario Outline: Choice Made
    Given The User Group has decided on "<r_name>" for their final choice
    When I ask what the restaurant choice
    Then I should be informed that the choice is "<r_name>" at address "<r_address>" with longitude "<r_lng>" and latitude "<r_lat>"
    Examples:
      | r_name    | r_address         | r_lng | r_lat |
      | Boustan   | 123, rue chat     | 73.23 | 51.43 |
      | McDonalds | rue, st-catherine | 43.23 | 53.32 |

  Scenario: No Choice Made
    Given The User Group has not yet decided on a final choice
    When I ask what the restaurant choice
    Then I should be informed that there is no final choice