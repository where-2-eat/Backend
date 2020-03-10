Feature: Join Group
  As an existing user, I want to be able to join an existing group

  Background:
    Given The following users are already registered
      |full_name    |_username     |_password    |
      |Steve Jobs   |steve99       |iphoneX89    |
      |John Doe     |johnDoe02     |abcd123      |
      |Imad Dodin   |idodin        |abcd123      |
    Given  The following groups exist
      |userName   | groupName |
      | idodin    | myGroup   |
      | johnDoe02 | xGroup    |

  Scenario Outline: Join Group
    When User "<userName>" joins group "<groupName>"
    Then User "<userName>" should receive a message confirming that they are in "<groupName>"
    And User "<userName>" should be a member of group "<groupName>"
    And User "<userName>" should not be the admin of group "<groupName>"

    Examples:
      |userName   | groupName |
      | johnDoe02    | myGroup   |
      | idodin | xGroup    |

  Scenario Outline: Create Group (Already Admin)
    When User "<userName>" joins group "<groupName>"
    Then User "<userName>" should receive a message that they are already administrator of that group
    And UserGroup "<groupName>" should have "<userName>" as its administrator
    And UserGroup "<groupName>" should not have any members
    Examples:
      |userName   | groupName |
      | idodin    | myGroup   |
      | johnDoe02 | xGroup    |