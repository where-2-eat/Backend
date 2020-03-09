Feature: Create Group
  As an existing user, I want to be able to create a User Group that I will be administrator for

  Background:
    Given The following users are already registered
      |full_name    |_username     |_password    |
      |Steve Jobs   |steve99       |iphoneX89    |
      |John Doe     |johnDoe02     |abcd123      |
      |Imad Dodin   |idodin        |abcd123      |

  Scenario Outline: Create Group
    When User "<userName>" creates a group with name "<groupName>"
    Then User "<userName>" should receive a message confirming that UserGroup "<groupName>" was created
    And UserGroup "<groupName>" should have "<userName>" as its administrator
    And UserGroup "<groupName>" should not have any members
    Examples:
    |userName   | groupName |
    | idodin    | myGroup   |
    | johnDoe02 | xGroup    |

  Scenario Outline: Create Group (Existing Name)
    Given A group exists with name "<groupName>"
    When User "<userName>" creates a group with name "<groupName>"
    Then User "<userName>" should receive a message that a group with that name already exists
    Examples:
      |userName   | groupName |
      | idodin    | myGroup   |
      | johnDoe02 | xGroup    |