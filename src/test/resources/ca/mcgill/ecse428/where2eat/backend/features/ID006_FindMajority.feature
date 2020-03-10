Feature: Find Majority
  For an existing group of users, we should find the 3 restaurant types that is shared with the most amount of users in that group.

  Background:
    Given The following users and preferences are already registered
      |full_name    |_username     |_password    | preference1 | preference2 | preference3 |
      |Steve Jobs   |steve99       |iphoneX89    | lebanese    | burger      | portugese   |
      |John Doe     |johnDoe02     |abcd123      | lebanese    | chinese     | italian     |
      |Imad Dodin   |idodin        |abcd123      | lebanese    | burger      | portugese   |
    Given  The following groups exist with members
      |userName   | groupName | member1    | member2   |
      | idodin    | myGroup   | johnDoe02  | steve99   |
      | johnDoe02 | xGroup    | steve99    | idodin    |

  Scenario Outline: Vote Majority
    When I request the majority for group "<groupName>"
    Then The restaurant types returned should be "<preference1>" "<preference2>" "<preference3>"
    Examples:
      | groupName   | preference1   | preference2   | preference3   |
      | myGroup     | lebanese      | burger        | portugese     |

  Scenario Outline: Vote Majority (Empty Group)
    Given  The following groups exist
      |userName   | groupName |
      | idodin    | myGroup1   |
      | johnDoe02 | xGroup1    |
    When I request the majority for group "<groupName>"
    Then I should receive an error that the group has no members
    Examples:
      | groupName   | preference1   | preference2   | preference3   |
      | myGroup1     | lebanese      | burger        | portugese     |